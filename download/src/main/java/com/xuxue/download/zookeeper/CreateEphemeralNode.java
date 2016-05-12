package com.xuxue.download.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * Created by HanHan on 2016/5/10.
 */
public class CreateEphemeralNode implements Watcher {
    private static CountDownLatch connect = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper keeper = new ZooKeeper("192.168.24.85:2181", 5000, new CreateEphemeralNode());
        connect.await();
        String path1 = keeper.create("/test/node", "Hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("create a node");
        //   keeper.close ();

        while (true) {

            Thread.sleep(3000);
            System.out.println("loop a times");
            long sessionID = keeper.getSessionId();
            byte[] data = keeper.getSessionPasswd();
            connect = new CountDownLatch(1);
            keeper = new ZooKeeper("192.168.24.85:2181", 5000, new CreateEphemeralNode(), sessionID, data);
            connect.await();
        }

    }


    /*
        b88d95ad1fa2540d00f974cab45c795e035913e60ef11398bdecf2cd79a45e36930fff1af55867ff0303a862444ffd26798b7dc320c5059a91c08e33837fbfd40303880eb1057c198ae6a1669d1038ef
    * */

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connect.countDown();
        }
    }
}
