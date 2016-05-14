package com.xuxue.spider.download.impl.download;

import com.xuxue.spider.download.Downloader;
import com.xuxue.spider.event.DocumentSpiderEvent;
import com.xuxue.spider.event.SpiderEvent;
import com.xuxue.spider.pluging.Pluging;
import com.xuxue.spider.pool.HttpContexPool;
import com.xuxue.spider.pool.PlugingPool;
import com.xuxue.spider.pool.ProxyPool;
import com.xuxue.spider.pool.TaskCache;
import com.xuxue.spider.task.Task;

import java.util.List;

/**
 * Created by HanHan on 2016/5/13.
 */
public abstract class AbstractDownloader implements Downloader {

    private PlugingPool plugingPool;

    private TaskCache taskCache;

    private ProxyPool proxyPool;

    private HttpContexPool contexPool;


    @Override
    public void addPluging(Pluging pluging) {
        plugingPool.addPluging(pluging);
    }

    @Override
    public ProxyPool getProxyPool() {
        return this.proxyPool;
    }

    @Override
    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    @Override
    public TaskCache getTaskCache() {
        return this.taskCache;
    }

    @Override
    public void setTaskCache(TaskCache taskCache) {
        this.taskCache = taskCache;
    }

    @Override
    public HttpContexPool getHttpContexPool() {
        return this.contexPool;
    }

    @Override
    public void setHttpContexPool(HttpContexPool contexPool) {
        this.contexPool = contexPool;
    }

    @Override
    public PlugingPool getPlugingPool() {
        return this.plugingPool;
    }

    @Override
    public void setPlugingPool(PlugingPool plugingPool) {
        this.plugingPool = plugingPool;
    }

    protected void notifyBefor(DocumentSpiderEvent event) {
        Task task = event.getTask();
        List<Pluging> plugings = event.getPlugingPool().getPluging(task.getName());
        for (Pluging pluging : plugings) {
            pluging.befor(event);
        }
    }

    protected void notifyCreateChildTask(SpiderEvent event) {
        Task task = event.getTask();
        List<Pluging> plugings = event.getPlugingPool().getPluging(task.getName());
        for (Pluging pluging : plugings) {
            pluging.createChildTask(event);
        }

    }

    protected void notifyExceptionThrow(SpiderEvent event) {
        Task task = event.getTask();
        List<Pluging> plugings = event.getPlugingPool().getPluging(task.getName());
        for (Pluging pluging : plugings) {
            pluging.exceptionThrow(event);
        }
    }

    protected void notifyPipeLine(SpiderEvent event) {
        Task task = event.getTask();
        List<Pluging> plugings = event.getPlugingPool().getPluging(task.getName());
        for (Pluging pluging : plugings) {
            pluging.pipeLine(event);
        }
    }


}
