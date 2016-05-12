package com.xuxue.download.task;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class TaskLeaveComparator implements Comparator<Task> {

    public static void main(String[] args) {

        PriorityQueue<DocumentTask> taskQueue = new PriorityQueue<DocumentTask>(10, new TaskLeaveComparator());
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            DocumentTask task = new DocumentTask();
            task.setPriority(r.nextInt());
            taskQueue.add(task);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(taskQueue.remove().getPriority());
        }

    }

    @Override
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
