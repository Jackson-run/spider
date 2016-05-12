package com.xuxue.download.zookeeper.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by HanHan on 2016/5/11.
 */
public class Test1 {

    static String path = "/test/test1/node4";

    public static CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.21.96:2181,192.168.21.85:2181,192.168.21.88:2181")
                .sessionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        return client;
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework client = Test1.getClient();
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("listenr call");
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        System.out.println("child add" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("child update" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("child remove" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    default:
                        System.out.println("child remove" + pathChildrenCacheEvent.getData().getPath());
                        break;
                }
            }
        });
        System.out.println("add listener");
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path + "/c4");
        Thread.sleep(Integer.MAX_VALUE);

    }

}
