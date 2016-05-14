package com.xuxue.spider.pool;

import org.apache.http.protocol.HttpContext;

/**
 * 对于有些网站  需要携带Cookie访问 这个接口维护了一个根据一个HttpContex池</br>
 * 访问网站之前 会先查询这个HttpContex池 如果有匹配的HttpContex 会使用这个HttpContex
 * <p>
 * Created by HanHan on 2016/5/12.
 */
public interface HttpContexPool {

    /**
     * 从HttpContex池中获取一个HttpContex
     *
     * @param host
     * @return
     */
    HttpContext getHttpContex(String host);

    /**
     * 向HttpContex池中增加一个HttpContex
     *
     * @param host
     * @param httpContext
     */
    void addHttpContex(String host, HttpContext httpContext);

    /**
     * 将这个HttpContex 返回到池中
     *
     * @param httpContext
     */
    void backHttpContex(HttpContext httpContext);

}
