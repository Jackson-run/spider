package com.xuxue.download.pool;

import com.xuxue.download.task.Task;
import org.apache.http.annotation.ThreadSafe;

import java.util.List;


@ThreadSafe
public interface TaskPool {


    void put(String host, Task task);

    void put(List<Task> task);

    Task get(String host);

    Task get();

    int size();

}
