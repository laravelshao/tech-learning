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
public class ZookeeperAPITest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
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
        //zooKeeper.create("/zk-api-node", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取节点值
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/zk-api-node", null, stat);
        System.out.println(new String(data));

        // 设置节点值
        zooKeeper.setData("/zk-api-node", "1".getBytes(), stat.getVersion());
        byte[] data1 = zooKeeper.getData("/zk-api-node", null, stat);
        System.out.println(new String(data1));

        // 删除节点
        zooKeeper.delete("/zk-api-node", stat.getVersion());

        zooKeeper.close();

        System.in.read();
    }

}
