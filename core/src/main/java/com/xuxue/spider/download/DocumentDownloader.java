package com.xuxue.spider.download;

import com.xuxue.spider.pool.DocumentCache;


/**
 *这个类是文档下载器的抽象 这个类下载的内容是HTML文档
 * Created by HanHan on 2016/5/12.
 */
public interface DocumentDownloader extends Downloader {

    /**
     * 文档下载器要有一个文档池 下载过的文档放进文档池里 统一的解析{@linkplain DocumentCache}
     *
     * @param documentPool
     */
    void addDocumentPool(DocumentCache documentPool);

    /**
     * 获取这个文档下载器的文档池
     *
     * @return
     */
    DocumentCache getDocumentCache();

}
