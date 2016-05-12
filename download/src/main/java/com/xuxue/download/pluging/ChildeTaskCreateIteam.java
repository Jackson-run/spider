package com.xuxue.download.pluging;

import com.xuxue.download.task.DocumentTask;
import com.xuxue.download.task.Task;
import com.xuxue.download.util.Args;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HanHan on 2016/5/5.
 */
public class ChildeTaskCreateIteam implements TaskPluging {

    /**
     * 生成的子任务的任务名
     */
    private String name;

    /**
     * 要生成子任务的HTML文档的 host
     */
    private String host;

    /**
     * 要生成子任务的html的URI 必须要满足一定的格式
     */
    private String regex;


    /**
     * 生成的子任务的读取超时时间
     */
    private int readTimeout;

    /**
     * 生成的子任务连接超时时间
     */
    private int connectTimeout;

    /**
     * 生成的子任务从连接池获取连接的超时时间
     */
    private int getConnectionTimeout;

    /**
     * 从文档里选取子URL的css选择器
     */
    private String cssSelector;

    /**
     * 子任务的URL必须满足的格式
     */
    private String childTaskRegex;

    private String type;

    private int priority;

    public ChildeTaskCreateIteam(Element TaskElement) {

        this.name = TaskElement.select("name").text();
        this.host = TaskElement.select("host").text();
        this.regex = TaskElement.select("regex").text();
        this.readTimeout = Integer.parseInt(TaskElement.select("readtimeout").text());
        this.connectTimeout = Integer.parseInt(TaskElement.select("connecttimeout").text());
        this.getConnectionTimeout = Integer.parseInt(TaskElement.select("getConnectiontimeout").text());
        this.cssSelector = TaskElement.select("cssSelector").text();
        this.childTaskRegex = TaskElement.select("childTaskRegex").text();
        this.type = TaskElement.select("type").text();
        this.priority = Integer.parseInt(TaskElement.select("priority").text());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("name=" + name + " " + "host=" + host + " regex=" + regex + " readTimeout=" + readTimeout
                + "connectTimeout=" + connectTimeout + " getConnectionTimeout=" + getConnectTimeout() + " +cssSelector=" + cssSelector
                + "childTaskRegex=" + childTaskRegex + " type=" + type + " priority=" + priority);

        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getGetConnectionTimeout() {
        return getConnectionTimeout;
    }

    public void setGetConnectionTimeout(int getConnectionTimeout) {
        this.getConnectionTimeout = getConnectionTimeout;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    public String getChildTaskRegex() {
        return childTaskRegex;
    }

    public void setChildTaskRegex(String childTaskRegex) {
        this.childTaskRegex = childTaskRegex;
    }

    @Override
    public List<Task> getChilderTask(Task task, Document doc) {
        Args.notNULL(task);
        Args.notNULL(doc);
        List<Task> tasks = null;
        if (isMatch(task)) {
            Args.notNULL(cssSelector);
            Elements elem = doc.select(cssSelector);
            Args.notNULL(elem);
            tasks = new LinkedList<Task>();
            for (Element e : elem) {
                String url = e.absUrl("href");

                if (url.matches(childTaskRegex)) {
                    HttpGet get = new HttpGet(url);
                    Task ctask = null;
                    if (type.equals("document")) {
                        ctask = new DocumentTask();
                    }
                    ctask.setUriRequest(get);
                    ctask.setPriority(priority);
                    ctask.setName(name);
                    ctask.setConnectTimeout(connectTimeout);
                    ctask.setDegree(task.getDegree() + 1);
                    ctask.setContex(task.getContex());
                    ctask.setFatherURI(task.getHttpUriRequest().getURI());
                    ctask.setReadTimeout(readTimeout);
                    ctask.setGetConnectionTimeout(getConnectionTimeout);
                    ctask.setMaxDegree(task.getMaxDegree());
                    tasks.add(ctask);
                }
            }
            return tasks;
        }

        return null;
    }

    @Override
    public boolean isMatch(Task task) {
        String host = task.getHost();
        String url = task.getHttpUriRequest().getURI().toString();
        return host.equals(this.host) && url.matches(this.regex);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
