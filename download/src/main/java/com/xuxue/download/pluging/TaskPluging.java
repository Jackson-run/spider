package com.xuxue.download.pluging;

import com.xuxue.download.task.Task;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 这个URL插件  用户可以自定义一个文档生成子任务的过程
 * <p>
 * Created by HanHan on 2016/5/5.
 */
public interface TaskPluging {

    List<Task> getChilderTask(Task task, Document doc);

    boolean isMatch(Task task);

    String getHost();
}
