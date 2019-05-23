package com.laravelshao.learning.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author shaoqinghua
 * @date 2018/10/17
 * @description
 */
public class CuratorTest {

    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        curatorFramework.start();

        // 创建节点
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator-node", "1".getBytes());

        // 获取节点值
        Stat stat = new Stat();
        byte[] data = curatorFramework.getData().storingStatIn(stat).forPath("/curator-node");
        System.out.println(new String(data));

        // 设置节点值
        curatorFramework.setData().withVersion(stat.getVersion()).forPath("/curator-node", "2".getBytes());
        byte[] data1 = curatorFramework.getData().storingStatIn(stat).forPath("/curator-node");
        System.out.println(new String(data1));
        // 删除节点
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator-node");
    }
}
