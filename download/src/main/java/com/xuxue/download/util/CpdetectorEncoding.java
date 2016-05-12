package com.xuxue.download.util;

import info.monitorenter.cpdetector.io.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * <p>
 * 获取流编码,不保证完全正确，设置检测策略 isFast为true为快速检测策略，false为正常检测
 * InputStream 支持mark,则会在检测后调用reset，外部可重新使用。
 * InputStream 流没有关闭。
 * </p>
 * <p>
 * <p>
 * 如果采用快速检测编码方式,最多会扫描8个字节，依次采用的{@link UnicodeDetector}，，
 * {@link JChardetFacade}， {@link ASCIIDetector}检测。对于一些标准的unicode编码，适合这个方式或者对耗时敏感的。
 * </p>
 * <p>
 * <p>
 * 采用正常检测，读取指定字节数，如果没有指定，默认读取全部字节检测，依次采用的，，{@link JChardetFacade}， {@link ASCIIDetector}检测。
 * 字节越多检测时间越长，正确率较高。
 * </p>
 *
 * @author xuxue
 * @time 2016-2-28
 */
public class CpdetectorEncoding {

    private static final Logger logger = Logger.getLogger(CpdetectorEncoding.class);
    //default strategy use fastDtector
    private static final boolean DEFALUT_DETECT_STRATEGY = true;
    private static final int MAX_READBYTE_FAST = 8;
    private static CodepageDetectorProxy detector = null;
    private static CodepageDetectorProxy fastDtector = null;
    private static ParsingDetector parsingDetector = new ParsingDetector(false);
    private static ByteOrderMarkDetector byteOrderMarkDetector = new ByteOrderMarkDetector();

    /**
     * <p>
     * 获取流编码,不保证完全正确，设置检测策略 isFast为true为快速检测策略，false为正常检测
     * InputStream 支持mark,则会在检测后调用reset，外部可重新使用。
     * InputStream 流没有关闭。
     * </p>
     * <p>
     * <p>
     * 如果采用快速检测编码方式,最多会扫描8个字节，依次采用的{@link UnicodeDetector}，
     * {@link JChardetFacade}， {@link ASCIIDetector}检测。对于一些标准的unicode编码，适合这个方式或者对耗时敏感的。
     * </p>
     * <p>
     * <p>
     * 采用正常检测，读取指定字节数，如果没有指定，默认读取全部字节检测，依次采用的，{@link JChardetFacade}， {@link ASCIIDetector}检测。
     * 字节越多检测时间越长，正确率较高。
     * </p>
     *
     * @return Charset The character are now - hopefully - correct。如果为null，没有检测出来。
     * @throws IOException
     */
    public static Charset getEncoding(InputStream buffIn, boolean isFast) throws IOException {
        return getEncoding(buffIn, buffIn.available(), isFast);
    }

    public static Charset getFastEncoding(InputStream buffIn) throws IOException {
        return getEncoding(buffIn, MAX_READBYTE_FAST, DEFALUT_DETECT_STRATEGY);
    }


    public static Charset getEncoding(InputStream in, int size, boolean isFast) throws IOException {

        try {

            Charset charset = null;

            int tmpSize = in.available();
            size = size > tmpSize ? tmpSize : size;
            //if in support mark method,
            if (in.markSupported()) {

                if (isFast) {

                    size = size > MAX_READBYTE_FAST ? MAX_READBYTE_FAST : size;
                    in.mark(size++);
                    charset = getFastDetector().detectCodepage(in, size);
                } else {

                    in.mark(size++);
                    charset = getDetector().detectCodepage(in, size);
                }
                in.reset();

            } else {

                if (isFast) {

                    size = size > MAX_READBYTE_FAST ? MAX_READBYTE_FAST : size;
                    charset = getFastDetector().detectCodepage(in, size);
                } else {
                    charset = getDetector().detectCodepage(in, size);
                }
            }


            return charset;
        } catch (IllegalArgumentException e) {

            logger.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {

            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    public static Charset getEncoding(byte[] byteArr, boolean isFast) throws IOException {

        return getEncoding(byteArr, byteArr.length, isFast);
    }


    public static Charset getFastEncoding(byte[] byteArr) throws IOException {

        return getEncoding(byteArr, MAX_READBYTE_FAST, DEFALUT_DETECT_STRATEGY);
    }


    public static Charset getEncoding(byte[] byteArr, int size, boolean isFast) throws IOException {

        size = byteArr.length > size ? size : byteArr.length;
        if (isFast) {
            size = size > MAX_READBYTE_FAST ? MAX_READBYTE_FAST : size;
        }

        ByteArrayInputStream byteArrIn = new ByteArrayInputStream(byteArr, 0, size);
        BufferedInputStream in = new BufferedInputStream(byteArrIn);

        try {

            Charset charset = null;
            if (isFast) {

                charset = getFastDetector().detectCodepage(in, size);
            } else {

                charset = getDetector().detectCodepage(in, size);
            }

            return charset;
        } catch (IllegalArgumentException e) {

            logger.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {

            logger.error(e.getMessage(), e);
            throw e;
        }

    }


    private static CodepageDetectorProxy getDetector() {

        if (detector == null) {

            detector = CodepageDetectorProxy.getInstance();
            // Add the implementations of info.monitorenter.cpdetector.io.ICodepageDetector:
            // This one is quick if we deal with unicode codepages:
            detector.add(byteOrderMarkDetector);
            // The first instance delegated to tries to detect the meta charset attribut in html pages.
            detector.add(parsingDetector);
            // This one does the tricks of exclusion and frequency detection, if first implementation is
            // unsuccessful:
            detector.add(JChardetFacade.getInstance());
            detector.add(ASCIIDetector.getInstance());
        }

        return detector;
    }


    private static CodepageDetectorProxy getFastDetector() {

        if (fastDtector == null) {
            fastDtector = CodepageDetectorProxy.getInstance();
            fastDtector.add(UnicodeDetector.getInstance());
            fastDtector.add(byteOrderMarkDetector);
            fastDtector.add(JChardetFacade.getInstance());
            fastDtector.add(ASCIIDetector.getInstance());
        }

        return fastDtector;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://tools.jb51.net/table/http_header");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(connection.getInputStream());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[512];
        int size = 0;
        while (true) {
            size = in.read(buffer);
            if (size != -1) {
                out.write(buffer, 0, buffer.length);
            } else {
                break;
            }
        }
        System.out.println(getEncoding(out.toByteArray(), false));
    }
}
