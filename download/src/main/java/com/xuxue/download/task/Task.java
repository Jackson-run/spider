package com.xuxue.download.task;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;

import java.io.Serializable;
import java.net.URI;

/**
 * 对任务的抽象
 * <p>
 * 所有的任务都放在任务池里  任务池里面
 * <p>
 * 一个任务包含
 * 1.任务的类型  文档类型(html) Json   图片  flash  文件
 * 2.任务的请求( HttpUriRequest)
 * 3.任务的优先级  这个优先级与调度先后顺序相关  如果优先级越高 这个任务会优先被执行
 * 4.任务的名字  同一类的任务应该有相同的名字
 * 5.任务连接超时时间
 * 5.任务的读取超时时间
 * 6.从连接池获取连接的超时时间
 * 7.任务URL的深度
 * 8.这一类URL的最大深度
 * 9、任务的host
 * <p>
 * Created by HanHan on 2016/5/4.
 */
public interface Task extends Serializable {
    /**
     * @return 任务的类型
     */
    TaskType getTaskType();

    /**
     * @param type 任务类型
     */
    void setTaskType(TaskType type);

    /**
     * @return 任务的请求
     */
    HttpUriRequest getHttpUriRequest();

    /**
     * @param request 任务的请求
     */
    void setUriRequest(HttpUriRequest request);

    /**
     * @return 任务的优先级
     */
    int getPriority();

    /**
     * @param proority 任务的优先级
     */
    void setPriority(int proority);

    /**
     * @return 任务的名字
     */
    String getName();

    /**
     * @param name 任务的名字
     */
    void setName(String name);

    /**
     * @return 任务的连接超时时间
     */
    int getConnectTimeout();

    /**
     * @param connectTimeout 连接超时时间
     */
    void setConnectTimeout(int connectTimeout);

    /**
     * @return 任务得读取超时时间
     */
    int getReadTimeout();

    /**
     * @param readTimeout 读取的超时时间
     */
    void setReadTimeout(int readTimeout);

    /**
     * @return 任务获取连接得超时时间
     */
    int getGetConnectionTimeout();

    /**
     * @param getConnectionTimeout 任务从连接池获取连接的超时时间
     */
    void setGetConnectionTimeout(int getConnectionTimeout);

    /**
     * @return 任务的深度
     */
    int getDegree();

    /**
     * @param degree 任务的深度
     */
    void setDegree(int degree);

    /**
     * @return 任务的最大深度
     */
    int getMaxDegree();

    void setMaxDegree(int maxdegree);

    /**
     * @return 任务的host
     */
    String getHost();

    /**
     * @return 父任务的URI
     */
    URI getFatherURI();

    /**
     * @param uri 父任务的URI
     */
    void setFatherURI(URI uri);

    HttpClientContext getContex();

    void setContex(HttpClientContext contex);
}
