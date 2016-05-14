package com.xuxue.spider.task;

/**
 * Created by HanHan on 2016/5/12.
 */
public enum TaskProcessStatus {
    /**
     * 访问并且获取内容成功
     */
    SUCCESS,
    /**
     * 访问超时
     */
    TIMEOUT,
    /**
     * 返回码错误
     */
    ERROR,

    /**
     * 任务被取消了
     */
    CANCELED,

    /**
     * 即将被执行
     */
    WILLPROCESS,

    /**
     * 正在被执行
     */
    PROCESSING,
}
