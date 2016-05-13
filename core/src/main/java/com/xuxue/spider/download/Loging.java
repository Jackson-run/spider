package com.xuxue.spider.download;

/**
 * 这个是登陆的接口 登陆会产生一个HttpContex对象
 * Created by HanHan on 2016/5/13.
 */
public interface Loging {

    HostedHttpContex login(String host, String name, String passwd);

}
