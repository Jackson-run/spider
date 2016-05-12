package com.xuxue.download.zookeeper.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by HanHan on 2016/5/12.
 */
public class Test3 {

    public static String path = "/test/test3/node";

    public static void main(String[] args) {

        CuratorFramework client = Test1.getClient();
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, path);
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                        lock.acquire();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat formate = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = formate.format(new Date());
                    System.out.println("生成的订单号是:" + orderNo);
                    try {
                        lock.release();
                    } catch (Exception e) {

                    }

                }
            }).start();
        }

        down.countDown();

    }
}
