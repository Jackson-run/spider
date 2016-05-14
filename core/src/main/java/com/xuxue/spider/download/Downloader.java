package com.xuxue.spider.download;

import com.xuxue.spider.pluging.Pluging;
import com.xuxue.spider.pool.HttpContexPool;
import com.xuxue.spider.pool.PlugingPool;
import com.xuxue.spider.pool.ProxyPool;
import com.xuxue.spider.pool.TaskCache;
import com.xuxue.spider.task.Task;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 对下载器的抽象 所有的下载器必须要实现这个类的方法
 * Created by HanHan on 2016/5/12.
 *
 */
public interface Downloader {

    /**
     * 为这个下载器添加一个插件{@linkplain Pluging}
     */
    void addPluging(Pluging pluging);

    /**
     * 获取这个下载器的代理池{@linkplain ProxyPool}
     *
     *
     */
    ProxyPool getProxyPool();

    /**
     * 为这个下载器添加一个代理池{@linkplain ProxyPool}
     *
     * @param proxyPool
     */
    void setProxyPool(ProxyPool proxyPool);

    /**
     * 获取这个下载器的任务缓存
     * @return
     */
    TaskCache getTaskCache();

    /**
     * 为这个下载器添加一个任务池{@linkplain TaskCache}
     *
     * @param taskPool
     */
    void setTaskCache(TaskCache taskPool);

    /**
     *返回这个下载器的HttpContex池{@linkplain HttpContexPool}
     * @return
     */
    HttpContexPool getHttpContexPool();

    /**
     * 每一个Downloader下载内容的时候要有一个HttpContex{@linkplain org.apache.http.protocol.HttpContext}
     * 这个HttpContex对象用来保存一个特定网站的Cookie和 访问这个这个特定的网站的超时时间
     *
     * @param contexPool
     */
    void setHttpContexPool(HttpContexPool contexPool);

    /**
     * 返回这个下载器的插件池
     *
     * @return
     */
    PlugingPool getPlugingPool();

    /**
     * 设置这个下载器的插件池
     * @param plugingPool
     */
    void setPlugingPool(PlugingPool plugingPool);

    /**
     * 处理一个文件任务 返回一个文件
     *
     * @param task
     * @return
     */
    void process(Task task);

    /**
     * 返回这个下载器的HttpClient{@linkplain org.apache.http.client.HttpClient}的ConnectionManager{@linkplain PoolingHttpClientConnectionManager}
     *
     * @return
     */
    PoolingHttpClientConnectionManager getConnectionManager();
}
