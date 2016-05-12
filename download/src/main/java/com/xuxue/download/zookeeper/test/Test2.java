package com.xuxue.download.zookeeper.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * Created by HanHan on 2016/5/12.
 */
public class Test2 {
    static String path = "/test/master";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = Test1.getClient();
        client.start();
        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为 master");
                Thread.sleep(3000);
                System.out.println("完成Master 释放权利");
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
