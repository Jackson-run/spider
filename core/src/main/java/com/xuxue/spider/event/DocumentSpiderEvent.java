package com.xuxue.spider.event;

import org.jsoup.nodes.Document;

/**
 * Created by HanHan on 2016/5/12.
 */
public interface DocumentSpiderEvent extends SpiderEvent {

    /**
     * 返回与这个任务的Document{@linkplain Document}
     *
     * @return
     */
    Document getDocument();

    /**
     * 设置这个事件相关的Document
     *
     * @param document
     */
    void setDocument(Document document);
}
