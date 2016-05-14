package com.xuxue.spider.download.impl.event;

import com.xuxue.spider.event.DocumentSpiderEvent;
import org.jsoup.nodes.Document;

/**
 * Created by HanHan on 2016/5/14.
 */
public class IDocumentEvent extends ISpiderEvent implements DocumentSpiderEvent {

    private Document document;

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public void setDocument(Document document) {
        this.document = document;
    }
}
