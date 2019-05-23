package com.laravelshao.learning.concurrent.deadlock;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class DeadLock implements Runnable {

    /**
     * 定义2个锁对象
     */
    private Object lock_A = new Object();
    private Object lock_B = new Object();

    /**
     * 定义boolean类型的变量，目的是切换线程到不同的代码区域中去运行
     */
    public boolean flag = true;

    @Override
    public void run() {
        //判断flag标记，目的是让切换一个进入if一个进入else执行
        if (flag) {
            synchronized (lock_A) {
                System.out.println(Thread.currentThread().getName() + " if  lock_A");

                synchronized (lock_B) {
                    System.out.println(Thread.currentThread().getName() + " if  lock_B");
                    //任务
                }
            }
        } else {
            synchronized (lock_B) {
                System.out.println(Thread.currentThread().getName() + " else ......  lock_B");

                synchronized (lock_A) {
                    System.out.println(Thread.currentThread().getName() + " else ......  lock_A");
                    //任务
                }
            }
        }
    }

}
