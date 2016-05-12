package com.xuxue.download.event;

import com.xuxue.download.pluging.PlugingPool;
import com.xuxue.download.pool.TaskPool;
import com.xuxue.download.task.Task;
import org.jsoup.nodes.Document;

import java.nio.charset.Charset;

public class DownloadEvent {

    public static final int READED = 1;

    public static final int EXCEPTION = 2;
    public static int status;
    private int responseCode;
    private Charset charset;
    private Document document;
    private Throwable exception;
    private TaskPool pool;
    private Task task;
    private PlugingPool plugingPool;

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        DownloadEvent.status = status;
    }

    public static int getReaded() {
        return READED;
    }

    public PlugingPool getPlugingPool() {
        return plugingPool;
    }

    public void setPlugingPool(PlugingPool plugingPool) {
        this.plugingPool = plugingPool;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public TaskPool getPool() {
        return pool;
    }

    public void setPool(TaskPool pool) {
        this.pool = pool;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task father) {
        this.task = father;
    }


}
