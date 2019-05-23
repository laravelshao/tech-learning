package com.laravelshao.learning.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author shaoqinghua
 * @date 2018/10/17
 * @description
 */
public class ZookeeperAPIWatcherTest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("默认事件：" + watchedEvent.getType());
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    // 收到服务端响应事件连接成功
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        // CONNECTED
        System.out.println(zooKeeper.getState());

        // 创建节点
        zooKeeper.create("/zk-api-node", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // exists/getData/getChildren
        // 通过exists绑定事件
        Stat stat = zooKeeper.exists("/zk-api-node", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType() + "->" + watchedEvent.getPath());
                try {
                    /**
                     * true 默认使用创建zookeeper绑定的watcher事件
                     */
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        stat = zooKeeper.setData("/zk-api-node", "1".getBytes(), stat.getVersion());

        zooKeeper.delete("/zk-api-node", stat.getVersion());

        System.in.read();
    }
}
