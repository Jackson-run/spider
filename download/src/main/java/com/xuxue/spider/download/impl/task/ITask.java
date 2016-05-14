package com.xuxue.spider.download.impl.task;

import com.xuxue.spider.task.Task;
import com.xuxue.spider.task.TaskProcessStatus;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by HanHan on 2016/5/14.
 */
public class ITask implements Task {

    private HttpUriRequest request;

    private String name;

    private int priority;

    private int degree;

    private int errorTimes;

    private int maxErrorTimes;

    private TaskProcessStatus processStatus;


    @Override
    public HttpUriRequest getHttpUriRequest() {
        return request;
    }

    @Override
    public void setHttpUriRequest(HttpUriRequest request) {
        this.request = request;
    }

    @Override
    public String getHost() {
        return request.getURI().getHost();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setString(String name) {
        this.name = name;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getDegree() {
        return this.degree;
    }

    @Override
    public void setDegree(int degree) {
        this.degree = degree;
    }

    @Override
    public int getErrorTimes() {
        return this.errorTimes;
    }

    @Override
    public void setErrorTimes(int errorTimes) {
        this.errorTimes = errorTimes;
    }

    @Override
    public int getMaxErrorTimes() {
        return this.maxErrorTimes;
    }

    @Override
    public void setMaxErrorTimes(int maxErrorTimes) {
        this.maxErrorTimes = maxErrorTimes;
    }

    @Override
    public TaskProcessStatus getTaskProcessStatus() {
        return this.processStatus;
    }

    @Override
    public void setTaskProcessStatus(TaskProcessStatus status) {
        this.processStatus = status;
    }
}
