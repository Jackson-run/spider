package com.xuxue.download.event;


public interface DownloadListener {

    void readed(DownloadEvent event);


    void pipeLine(DownloadEvent event);

    String getName();
}
