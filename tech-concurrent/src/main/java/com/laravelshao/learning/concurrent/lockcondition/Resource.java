package com.laravelshao.learning.concurrent.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
     * 定义JDK5之后的Lock锁
     */
    private Lock loc = new ReentrantLock();

    /**
     * 定义监视生产者线程的等待和唤醒对象
     */
    private Condition proCon = loc.newCondition();

    /**
     * 定义监视消费者线程的等待和唤醒对象
     */
    private Condition conCon = loc.newCondition();

    /**
     * 保存数据
     */
    public void save(Object obj) {
        //手动获取锁对象
        loc.lock();
        try {
            if (objs[0] != null) {
                //存在数据，保存线程不能执行保存操作，需要等待
                try {
                    proCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            objs[0] = obj;
            System.out.println(Thread.currentThread().getName() + " 正在保存的数据：" + objs[0]);
            //在生产者保存完数据后，需要唤醒消费线程
            conCon.signal();
        } finally {
            //手动释放锁对象
            loc.unlock();
        }
    }

    /**
     * 取出数据
     */
    public void get() {
        //手动获取锁对象
        loc.lock();
        try {
            if (objs[0] == null) {
                //没有数据，无法取出，需要等待
                try {
                    conCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "正在取出的数据：：：：：：：" + objs[0]);
            objs[0] = null;
            //消费结束，需要通知生产者线程
            proCon.signal();
        } finally {
            //手动释放锁对象
            loc.unlock();
        }
    }
}

