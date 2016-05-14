package com.xuxue.spider.event;

import com.xuxue.spider.pool.PlugingPool;
import com.xuxue.spider.pool.TaskCache;
import com.xuxue.spider.task.Task;
import com.xuxue.spider.task.TaskProcessStatus;

/**
 * 向插件传递的事件的抽象
 * Created by HanHan on 2016/5/12.
 */
public interface SpiderEvent {

    /**
     * 获得任务池
     *
     * @return {@linkplain TaskCache}
     */
    TaskCache getTaskCache();

    /**
     * 设置与这个任务关联的任务池
     *
     * @param taskPool
     */
    void setTaskCache(TaskCache taskPool);

    /**
     * 返回产生这个事件的任务
     *
     * @return
     */
    Task getTask();

    /**
     * 设置与这个事件相关联的任务
     *
     * @param task
     */
    void setTask(Task task);

    /**
     * 获取任务执行的状态
     *
     * @return
     */
    TaskProcessStatus getProcessStatus();

    /**
     * 设置这个事件相关联的任务的执行状态
     *
     * @param taskProcessStatus
     */
    void setTaskProcessStatus(TaskProcessStatus taskProcessStatus);

    /**
     * 获取绑定到下载器的PlugingPool
     *
     * @return
     */
    PlugingPool getPlugingPool();

    /**
     * 设置与这个事件相关联的插件池
     *
     * @param plugingPool
     */
    void setPlugingPool(PlugingPool plugingPool);

    /**
     * 获取下载过程中抛出的异常
     *
     * @return
     */
    Throwable getException();

    /**
     * 设置与这个事件相关联的异常
     *
     * @param throwable
     */
    void setThrowable(Throwable throwable);

    /**
     * 得到执行http请求时的返回码
     *
     * @return
     */
    int getResponseCode();

    /**
     * @param responseCode
     */
    void setResponseCode(int responseCode);
}
