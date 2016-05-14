package com.xuxue.tools.stream;

import com.xuxue.tools.charset.CpdetectorEncoding;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by HanHan on 2016/5/14.
 */
public class ReadStream {

    public static byte[] toBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[8 * 10 * 2];
            int readedSize = 0;
            while ((readedSize = in.read(buffer, 0, 128)) != -1) {
                out.write(buffer, 0, readedSize);
            }
            return out.toByteArray();
        } finally {
            in.close();
        }

    }

    public static String toString(InputStream in) throws IOException {
        byte[] data = toBytes(in);
        Charset charset = CpdetectorEncoding.getEncoding(data, false);
        return new String(data, charset.name());
    }

    public static Document toDocument(InputStream in, String url) throws IOException {

        String string = toString(in);
        Document doc = Jsoup.parse(string, url);
        return doc;
    }
}
