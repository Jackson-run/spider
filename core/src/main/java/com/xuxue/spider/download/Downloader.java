package com.xuxue.spider.download;

import com.xuxue.spider.parser.Pluging;
import com.xuxue.spider.pool.HttpContexPool;
import com.xuxue.spider.pool.PlugingPool;
import com.xuxue.spider.pool.ProxyPool;
import com.xuxue.spider.pool.TaskCache;
import com.xuxue.spider.task.Task;

/**
 * 对下载器的抽象 所有的下载器必须要实现这个类的方法
 * Created by HanHan on 2016/5/12.
 *
 */
public interface Downloader {
    /**
     * 启动这个下载器
     */
    void start();

    /**
     * 为这个下载器添加一个插件{@linkplain Pluging}
     */
    void addPluging(Pluging pluging);

    /**
     * 为这个下载器添加一个代理池{@linkplain ProxyPool}
     *
     * @param proxyPool
     */
    void setProxyPool(ProxyPool proxyPool);

    /**
     * 为这个下载器添加一个任务池{@linkplain com.xuxue.spider.pool.TaskCache}
     *
     * @param taskPool
     */
    void setTaskPool(TaskCache taskPool);

    /**
     * 每一个Downloader下载内容的时候要有一个HttpContex{@linkplain org.apache.http.protocol.HttpContext}
     * 这个HttpContex对象用来保存一个特定网站的Cookie和 访问这个这个特定的网站的超时时间
     *
     * @param contexPool
     */
    void setHttpContexPool(HttpContexPool contexPool);

    /**
     * 返回这个下载器
     *
     * @return
     */
    PlugingPool getPlugingPool();

    /**
     * 处理一个文件任务 返回一个文件
     *
     * @param task
     * @return
     */
    void process(Task task) throws Throwable;
}
