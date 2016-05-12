package com.xuxue.download.pluging;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 这是一个插件池的抽象
 * Created by HanHan on 2016/5/6.
 */
public interface PlugingPool extends TaskPluging {

    void addPluging(TaskPluging pluging);

    void addPluging(File config) throws IOException;

    List<TaskPluging> getPluging(String host);

    int size();
}
