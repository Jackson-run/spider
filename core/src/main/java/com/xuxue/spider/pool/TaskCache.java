package com.xuxue.spider.pool;

import com.xuxue.spider.task.Task;

/**
 * 任务的缓存
 * 这个缓存对象必须能实现添加Task 获取Task
 * <p>
 * 任务的缓存要尽量分散不同的主机名的任务  降低访问一个主机的频率 这样来提高效率
 * Created by HanHan on 2016/5/12.
 */
public interface TaskCache {
    /**
     * 向缓存中添加一个Task
     *
     * @param task
     */
    void addTask(Task task);

    /**
     * 从缓存中获取一个Task
     *
     * @return
     */
    Task getTask();

    /**
     * 根据一个主机来返回一个Task
     *
     * @param host
     * @return
     */
    Task getByHost(String host);

}
