package com.laravelshao.learning.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoqinghua
 * @date 2018/10/20
 * @description
 */
public class ZkClientWatcherTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        ZkClient zkClient = new ZkClient("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181", 4000);
        System.out.println("连接成功");

        // Watcher
        // 订阅节点内容修改事件
        zkClient.subscribeDataChanges("/node", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点：" + s + " 修改后值：" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        zkClient.writeData("/node", "haha");
        TimeUnit.SECONDS.sleep(2);

        // 订阅子节点内容修改事件
        zkClient.subscribeChildChanges("/node", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("节点：" + s + " 修改后子节点列表：" + list);
            }
        });

        System.in.read();
    }
}
