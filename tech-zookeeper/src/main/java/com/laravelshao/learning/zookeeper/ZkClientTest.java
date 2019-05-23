package com.laravelshao.learning.zookeeper;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @author shaoqinghua
 * @date 2018/10/20
 * @description
 */
public class ZkClientTest {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181", 4000);
        System.out.println("连接成功");

        // 创建节点(提供递归创建父节点功能)
        //zkClient.createPersistent("/zkclient/zk-1/zk-1-1", true);
        //System.out.println("创建多级节点成功");

        // 删除节点(递归删除节点)
        //zkClient.deleteRecursive("/zkclient");
        //System.out.println("删除多级节点成功");

        // 获取节点
        List<String> children = zkClient.getChildren("/node");
        System.out.println(children);
    }
}
