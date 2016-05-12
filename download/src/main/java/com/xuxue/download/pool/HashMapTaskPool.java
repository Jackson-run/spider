package com.xuxue.download.pool;

import com.xuxue.download.task.Task;
import com.xuxue.download.task.TaskLeaveComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class HashMapTaskPool implements TaskPool {

    HashMap<String, PriorityBlockingQueue<Task>> map;

    public HashMapTaskPool() {
        this.map = new HashMap<String, PriorityBlockingQueue<Task>>();
    }

    @Override
    public void put(String host, Task task) {
        PriorityBlockingQueue<Task> queue = map.get(host);
        if (queue == null) {
            queue = new PriorityBlockingQueue<Task>(11, new TaskLeaveComparator());
        }
        queue.add(task);
        map.put(host, queue);
    }

    @Override
    public void put(List<Task> tasks) {
        if (tasks == null)
            return;
        for (Task task : tasks) {
            put(task.getHost(), task);
        }
    }

    @Override
    public Task get(String host) {
        PriorityBlockingQueue<Task> queue = map.get(host);
        if (queue == null) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public Task get() {
        String tempKey = "";
        int maxsize = 0;
        Set<Map.Entry<String, PriorityBlockingQueue<Task>>> set = map.entrySet();
        for (Map.Entry<String, PriorityBlockingQueue<Task>> en : set) {
            int size = en.getValue().size();
            if (size > maxsize) {
                tempKey = en.getKey();
                maxsize = size;
            }
        }
        return get(tempKey);
    }

    @Override
    public int size() {
        Set<Map.Entry<String, PriorityBlockingQueue<Task>>> set = map.entrySet();
        int size = 0;
        for (Map.Entry<String, PriorityBlockingQueue<Task>> en : set) {
            size += en.getValue().size();
        }
        return size;
    }

}
