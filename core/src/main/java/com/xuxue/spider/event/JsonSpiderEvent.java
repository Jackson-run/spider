package com.xuxue.spider.event;

import com.xuxue.tools.json.JSONPathObject;

/**
 *
 * Created by HanHan on 2016/5/12.
 */
public interface JsonSpiderEvent extends SpiderEvent {

    /**
     * 返回这个JsonSpiderEvent相关的JSONPathObject
     *
     * @return
     */
    JSONPathObject getJsonPathObject();

    /**
     * 设置与这个JsonSpiderEvent相关的JSONPathObject
     *
     * @param jsonPathObject
     */
    void setJSONPathObject(JSONPathObject jsonPathObject);

}
