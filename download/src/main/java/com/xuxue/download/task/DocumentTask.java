package com.xuxue.download.task;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;

import java.net.URI;


public class DocumentTask implements Task {


    int degree;
    private TaskType taskType;
    private HttpUriRequest request;
    private int priority;
    private String name;
    private int connectTimeout;
    private int readTimeout;
    private int getConnectionTimeout;
    private int maxDegree;

    private URI fatherURI;

    private HttpClientContext context;

    public DocumentTask() {
        super();
        this.setTaskType(TaskType.DOCUMENT);
    }

    @Override
    public TaskType getTaskType() {
        return this.taskType;
    }

    @Override
    public void setTaskType(TaskType type) {
        this.taskType = type;
    }

    @Override
    public HttpUriRequest getHttpUriRequest() {
        return this.request;
    }

    @Override
    public void setUriRequest(HttpUriRequest request) {
        this.request = request;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(int proority) {
        this.priority = proority;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Override
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }


    @Override
    public int getReadTimeout() {
        return this.readTimeout;
    }

    @Override
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }


    @Override
    public int getGetConnectionTimeout() {
        return getConnectionTimeout;
    }

    @Override
    public void setGetConnectionTimeout(int getConnectionTimeout) {
        this.getConnectionTimeout = getConnectionTimeout;
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
    public int getMaxDegree() {
        return maxDegree;
    }

    @Override
    public void setMaxDegree(int maxdegree) {
        this.maxDegree = maxdegree;
    }

    @Override
    public String getHost() {
        if (this.request != null) {
            return request.getURI().getHost();
        }
        return null;
    }

    @Override
    public URI getFatherURI() {
        return fatherURI;
    }

    @Override
    public void setFatherURI(URI uri) {
        this.fatherURI = uri;
    }

    @Override
    public HttpClientContext getContex() {
        return this.context;
    }

    @Override
    public void setContex(HttpClientContext contex) {
        this.context = context;
    }
}
