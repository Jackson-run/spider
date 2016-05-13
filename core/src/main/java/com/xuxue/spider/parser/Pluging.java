package com.xuxue.spider.parser;

import com.xuxue.spider.event.SpiderEvent;

/**
 * 用户自定义的插件 插件用来处理{@linkplain com.xuxue.spider.event.SpiderEvent}事件的</br>
 *
 * 在每个插件里 用户必须定义好如何处理抓取任务{@linkplain com.xuxue.spider.task.Task}的结果</br>
 *
 * 每个插件必须要有一个唯一的名字  每一个任务也有一个唯一的名字  这两个名字是相关联的  一个任务下载产生的时间会被
 * 和这个任务同名的插件处理
 *
 * 抓取的任务结果已经被抽象成了一个个事件  同时我们还提供了很多有用的工具来处理抓取的文档{@linkplain com.xuxue.spider.task.DocumentTask}
 * Json数据{@linkplain com.xuxue.spider.task.JsonTask}
 * 文件数据{@linkplain com.xuxue.spider.task.FileTask}
 *
 * Created by HanHan on 2016/5/12.
 */
public interface Pluging {
    /**
     * 用户必须要定义如何产生子任务
     *
     * @param event
     */
    void createChildTask(SpiderEvent event);

    /**
     * 用户必须定义当异常抛出是改如何做
     *
     * @param event
     */
    void exceptionThrow(SpiderEvent event);

    /**
     * 用户必须定义该如何存抓取的结果取结果
     *
     * @param event
     */
    void pipeLine(SpiderEvent event);

    /**
     * 返回这个插件的名字
     *
     * @param name
     */
    void getName(String name);

}
