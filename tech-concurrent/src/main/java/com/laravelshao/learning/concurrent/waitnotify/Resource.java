package com.laravelshao.learning.concurrent.waitnotify;

/**
 * Created by shaoqinghua on 2018/5/3.
 * 定义一个负责保存和取出的资源类
 */
public class Resource {

    /**
     * 定义成员变量，充当保存和取出的容器
     */
    private Object[] objs = new Object[1];

    /**
     * 定义一个同步的锁对象
     */
    private Object lock = new Object();

    /**
     * 保存数据
     */
    public void save(Object obj) {
        synchronized (lock) {
            if (objs[0] != null) {
                //存在数据，保存线程不能执行保存操作，需要等待
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            objs[0] = obj;
            System.out.println(Thread.currentThread().getName() + " 正在保存的数据：" + objs[0]);
            //在生产者保存完数据后，需要唤醒消费线程
            lock.notify();
        }
    }

    /**
     * 取出数据
     */
    public void get() {
        synchronized (lock) {
            if (objs[0] == null) {
                //没有数据，无法取出，需要等待
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "正在取出的数据：：：：：：：" + objs[0]);
            objs[0] = null;
            //消费结束，需要通知生产者线程
            lock.notify();
        }
    }

}

