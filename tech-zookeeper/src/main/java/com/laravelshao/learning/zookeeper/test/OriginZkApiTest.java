package com.laravelshao.learning.zookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author shaoqinghua
 * @date 2019/1/29
 * @description
 */
public class OriginZkApiTest {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper =
                    new ZooKeeper("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181", 4000, new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                                countDownLatch.countDown();
                            }
                        }
                    });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());

            // 创建节点
            zooKeeper.create("/zk-origin-api-node", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //exists/getData/getChildren
            Stat stat = zooKeeper.exists("/zk-origin-api-node", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(watchedEvent.getType() + "->" + watchedEvent.getPath());
                    /**
                     * true 默认使用创建zookeeper绑定的watcher事件
                     */
                    try {
                        zooKeeper.exists("/zk-origin-api-node", true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            //// 获取节点
            //Stat stat = new Stat();
            //byte[] data = zooKeeper.getData("/zk-origin-api-node", null, stat);
            //System.out.println(new String(data));

            stat = zooKeeper.setData("/zk-origin-api-node", "1".getBytes(), stat.getVersion());

            zooKeeper.delete("/zk-origin-api-node", stat.getVersion());

            //// 更新节点
            //zooKeeper.setData("/zk-origin-api-node", "1".getBytes(), stat.getVersion());
            //byte[] data2 = zooKeeper.getData("/zk-origin-api-node", null, stat);
            //System.out.println(new String(data2));
            //
            //// 删除节点
            //zooKeeper.delete("/zk-origin-api-node", stat.getVersion());

            zooKeeper.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
