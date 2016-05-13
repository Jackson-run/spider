package com.xuxue.spider.event;

import java.io.File;

/**
 * Created by HanHan on 2016/5/12.
 */
public interface FileSpiderEvent extends SpiderEvent {

    /**
     * 获得这个下载文件事件下载到的文件
     *
     * @return
     */
    File getFile();

    /**
     * 设置这个文件下载器下载得到的文件
     *
     * @param file
     */
    void setFile(File file);

}
