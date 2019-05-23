package com.laravelshao.learning.zookeeper.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author shaoqinghua
 * @date 2019/1/29
 * @description
 */
public class CuratorWatcherTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("172.16.105.128:2181,172.16.105.129:2181,172.16.105.130:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        curatorFramework.start();


        /**
         * NodeCache：监听节点创建、删除事件
         * PathChildCache：监听节点下子节点创建、修改、删除事件
         * TreeCache：综合NodeCache和PathChildCache监听事件
         */

        addListenerWithNodeCache(curatorFramework, "/eva");
        addListenerWithPathChildCache(curatorFramework, "/mic");
        addListenerWithTreeCache(curatorFramework, "/mic");

        System.in.read();


    }

    /**
     * TreeCache：综合NodeCache和PathChildCache监听事件
     *
     * @param curatorFramework
     * @param path
     */
    private static void addListenerWithTreeCache(CuratorFramework curatorFramework, String path) throws Exception {

        TreeCache treeCache = new TreeCache(curatorFramework, path);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println("Receive Event:" + treeCacheEvent.getType());
            }
        };

        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }

    /**
     * PathChildCache：监听节点下子节点创建、修改、删除事件
     *
     * @param curatorFramework
     * @param path
     */
    private static void addListenerWithPathChildCache(CuratorFramework curatorFramework, String path) throws Exception {

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("Receive Event:" + pathChildrenCacheEvent.getType());
            }
        };

        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }

    /**
     * NodeCache：监听节点创建、删除事件
     *
     * @param curatorFramework
     * @param path
     */
    private static void addListenerWithNodeCache(CuratorFramework curatorFramework, String path) throws Exception {

        NodeCache nodeCache = new NodeCache(curatorFramework, path);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Event:" + nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
