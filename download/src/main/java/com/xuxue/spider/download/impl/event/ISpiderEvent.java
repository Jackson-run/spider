package com.xuxue.spider.download.impl.event;

import com.xuxue.spider.event.SpiderEvent;
import com.xuxue.spider.pool.PlugingPool;
import com.xuxue.spider.pool.TaskCache;
import com.xuxue.spider.task.Task;
import com.xuxue.spider.task.TaskProcessStatus;

/**
 * Created by HanHan on 2016/5/14.
 */
public abstract class ISpiderEvent implements SpiderEvent {

    private TaskCache taskCache;

    private Task task;

    private PlugingPool plugingPool;

    private Throwable exception;

    private int responseCode;

    @Override
    public TaskCache getTaskCache() {

        return taskCache;
    }

    @Override
    public void setTaskCache(TaskCache taskPool) {
        this.taskCache = taskPool;
    }

    @Override
    public Task getTask() {

        return this.task;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public TaskProcessStatus getProcessStatus() {
        return this.task.getTaskProcessStatus();
    }

    @Override
    public void setTaskProcessStatus(TaskProcessStatus taskProcessStatus) {
        this.task.setTaskProcessStatus(taskProcessStatus);
    }

    @Override
    public PlugingPool getPlugingPool() {
        return this.plugingPool;
    }

    @Override
    public void setPlugingPool(PlugingPool plugingPool) {
        this.plugingPool = plugingPool;
    }

    @Override
    public Throwable getException() {
        return this.exception;
    }

    @Override
    public void setThrowable(Throwable throwable) {
        this.exception = throwable;
    }

    @Override
    public int getResponseCode() {
        return this.responseCode;
    }

    @Override
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
