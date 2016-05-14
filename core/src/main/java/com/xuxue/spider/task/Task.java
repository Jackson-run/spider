package com.xuxue.spider.task;


import org.apache.http.client.methods.HttpUriRequest;



/**
 *
 * Created by HanHan on 2016/5/12.
 */
public interface Task {

    /**
     * 得到这个任务的请求信息
     *
     * @return
     */
    HttpUriRequest getHttpUriRequest();

    /**
     * 设置这个任务的请求信息
     *
     * @param request
     */
    void setHttpUriRequest(HttpUriRequest request);

    /**
     * 得到这个任务相关的URI的 host
     *
     * @return
     */
    String getHost();

    /**
     * 获取这个个任务的名字
     *
     * @return
     */
    String getName();

    /**
     * 设置这个任务的名字
     *
     * @param name
     */
    void setString(String name);

    /**
     * 获取这个任务的优先级 一个任务的优先级与他被执行的顺序相关  优先级越大 他被执行的可能性就越高
     *
     * @return
     */
    int getPriority();

    /**
     * 设置这个任务的优先级
     *
     * @param priority
     */
    void setPriority(int priority);

    /**
     * 返回任务在这个任务树中的深度
     *
     * @return
     */
    int getDegree();

    /**
     * @param degree
     */
    void setDegree(int degree);

    /**
     * 获取这个任务出错的次数
     *
     * @return
     */
    int getErrorTimes();

    /**
     * 设置这个任务出错的次数
     *
     * @param errorTimes
     */
    void setErrorTimes(int errorTimes);

    /**
     * 返回这个任务最大的出错次数
     *
     * @return
     */
    int getMaxErrorTimes();

    /**
     * 设置这个任务最大的出错次数
     *
     * @param maxErrorTimes
     */
    void setMaxErrorTimes(int maxErrorTimes);

    /**
     * 获取这个任务执行的状态
     *
     * @return
     */
    TaskProcessStatus getTaskProcessStatus();

    /**
     * 设置这个任务的执行状态
     *
     * @param status
     */
    void setTaskProcessStatus(TaskProcessStatus status);
}
