package com.laravelshao.learning.zookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author shaoqinghua
 * @date 2019/1/30
 * @description
 */
public class ZkDistributedLock implements Lock, Watcher {

    private ZooKeeper zooKeeper = null;

    /**
     * 根结点
     */
    private String ROOT_LOCK = "/locks";

    /**
     * 等待的前一个锁
     */
    private String WAIT_LOCK;

    /**
     * 当前锁
     */
    private String CURRENT_LOCK;

    private CountDownLatch countDownLatch;

    public ZkDistributedLock() {
        try {
            this.zooKeeper = new ZooKeeper("172.16.105.128:2181", 4000, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (stat == null) {
                zooKeeper.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + "获取锁成功");
        }

        try {
            waitingForLock(WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待获取锁
     *
     * @param prev
     * @return
     */
    private boolean waitingForLock(String prev) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(prev, true);
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + "->等待锁" + prev + " 释放");
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            //TODO  watcher触发以后，还需要再次判断当前等待的节点是不是最小的
            System.out.println(Thread.currentThread().getName() + "->获取锁成功");
        }
        return true;
    }

    @Override
    public boolean tryLock() {
        try {
            // 创建临时有序节点
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/", "0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + " 尝试竞争锁");
            List<String> children = zooKeeper.getChildren(ROOT_LOCK, false); // 获取根结点下所有子节点
            SortedSet<String> sortedSet = new TreeSet<>(); // 定义一个集合进行排序
            for (String child : children) {
                sortedSet.add(ROOT_LOCK + "/" + child);
            }
            String firstNode = sortedSet.first(); // 获取头节点
            if (CURRENT_LOCK.equals(firstNode)) {
                return true; // 获取锁成功
            }

            SortedSet<String> lessThan = ((TreeSet<String>) sortedSet).headSet(CURRENT_LOCK); // 获取小于当前节点的集合
            if (!lessThan.isEmpty()) {
                WAIT_LOCK = lessThan.last();
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "->释放锁" + CURRENT_LOCK);
        try {
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
