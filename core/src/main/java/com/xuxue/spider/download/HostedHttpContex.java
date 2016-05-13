package com.xuxue.spider.download;

import org.apache.http.protocol.HttpContext;

/**
 * 这个接口表示绑定到指定主机的HttpContex
 * Created by HanHan on 2016/5/13.
 */
public class HostedHttpContex {

    private final String host;

    private final HttpContext httpContex;

    private final String name;

    public HostedHttpContex(String host, String name, HttpContext httpContex) {
        this.host = host;
        this.httpContex = httpContex;
        this.name = name;
    }

    public String getHost() {
        return this.host;
    }

    public HttpContext getHttpContex() {
        return this.httpContex;
    }

    public String getName() {
        return this.name;
    }
}
