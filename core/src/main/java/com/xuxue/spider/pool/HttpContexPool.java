package com.xuxue.spider.pool;

import org.apache.http.protocol.HttpContext;

/**
 * 对于有些网站  需要携带Cookie访问 这个接口维护了一个根据一个HttpContex池</br>
 * 访问网站之前 会先查询这个HttpContex池 如果有匹配的HttpContex 会使用这个HttpContex
 * <p>
 * Created by HanHan on 2016/5/12.
 */
public interface HttpContexPool {

    HttpContext getHttpContex(String host);

    void addHttpContex();


}
