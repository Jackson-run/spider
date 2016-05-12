package com.xuxue.download.pluging;

import com.xuxue.download.task.Task;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by HanHan on 2016/5/6.
 */
public class DefaultPlugingPool implements PlugingPool {
    public HashMap<String, List<TaskPluging>> plugings;
    private Logger logger = Logger.getLogger(DefaultPlugingPool.class);

    public DefaultPlugingPool(File config) throws IOException {
        this();
        addPluging(config);
    }

    public DefaultPlugingPool() {
        plugings = new HashMap<String, List<TaskPluging>>();
    }

    @Override
    public List<Task> getChilderTask(Task task, Document doc) {
        List<Task> lists = new LinkedList<Task>();
        if (!isMatch(task)) {
            return lists;
        }
        List<TaskPluging> pluging = plugings.get(task.getHost());
        for (TaskPluging p : pluging) {
            if (p.isMatch(task)) {
                List<Task> ta = p.getChilderTask(task, doc);
                lists.addAll(ta);
            }
        }
        return lists;
    }

    @Override
    public boolean isMatch(Task task) {
        String host = task.getHost();
        List<TaskPluging> list = plugings.get(host);
        if (list == null || list.size() == 0) {
            return false;
        }
        for (TaskPluging t : list) {
            if (t.isMatch(task)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getHost() {
        throw new UnsupportedOperationException("插件池不支持的操作");
    }

    @Override
    public void addPluging(TaskPluging pluging) {
        String host = pluging.getHost();
        List<TaskPluging> plugingList = plugings.get(host);
        if (plugingList == null) {
            plugingList = new ArrayList<TaskPluging>();
        }
        plugingList.add(pluging);
        plugings.put(host, plugingList);
    }

    @Override
    public void addPluging(File config) throws IOException {
        Document doc = Jsoup.parse(config, "utf-8");
        Elements es = doc.select("Task");
        if (es == null || es.size() == 0) {
            throw new IOException("配置文件没用内容或者格式不符合");
        }
        for (Element e : es) {
            try {
                addPluging(new ChildeTaskCreateIteam(e));
            } catch (Exception ex) {
                continue;
            }
        }
    }

    @Override
    public List<TaskPluging> getPluging(String host) {
        return plugings.get(host);
    }

    @Override
    public int size() {
        Set<Map.Entry<String, List<TaskPluging>>> set = plugings.entrySet();
        int tempSize = 0;
        for (Map.Entry<String, List<TaskPluging>> s : set) {
            tempSize += s.getValue().size();
        }
        return tempSize;
    }
}
