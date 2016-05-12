package com.xuxue.download.event;

import com.xuxue.download.pluging.PlugingPool;
import com.xuxue.download.pool.TaskPool;
import com.xuxue.download.task.Task;

import java.util.List;

public class DefaultListener implements DownloadListener {

    @Override
    public void readed(DownloadEvent event) {
        if (DownloadEvent.getStatus() == DownloadEvent.READED && event.getResponseCode() == 200) {
            PlugingPool pool = event.getPlugingPool();
            try {
                System.out.println((pool == null) + "? pool==null");
                List<Task> tasks = pool.getChilderTask(event.getTask(), event.getDocument());
                System.out.println("add task" + tasks.size());
                TaskPool taskPool = event.getPool();
                taskPool.put(tasks);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.pipeLine(event);
    }

    @Override
    public void pipeLine(DownloadEvent event) {
        System.out.println(event.getDocument().title());
    }

    @Override
    public String getName() {
        return null;
    }

}
