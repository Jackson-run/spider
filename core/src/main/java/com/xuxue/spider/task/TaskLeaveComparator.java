package com.xuxue.spider.task;

/**
 * Created by HanHan on 2016/5/14.
 */


import java.util.Comparator;


public class TaskLeaveComparator implements Comparator<Task> {

    public int compare(Task o1, Task o2) {
        if (o1.getPriority() > o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() == o2.getPriority()) {
            return 0;
        } else {
            return 1;
        }
    }
}
