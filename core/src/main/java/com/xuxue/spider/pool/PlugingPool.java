package com.xuxue.spider.pool;

import com.xuxue.spider.pluging.Pluging;

import java.util.List;

/**
 * 插件池
 * Created by HanHan on 2016/5/12.
 */
public interface PlugingPool {
    /**
     * 向插件池中增加插件
     *
     * @param pluging
     */
    void addPluging(Pluging pluging);

    /**
     * 根据插件的名字获取插件
     *
     * @param name
     * @return
     */
    List<Pluging> getPluging(String name);

}
