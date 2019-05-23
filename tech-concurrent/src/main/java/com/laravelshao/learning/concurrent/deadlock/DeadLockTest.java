package com.laravelshao.learning.concurrent.deadlock;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class DeadLockTest {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 死锁问题测试
         */
        DeadLock task = new DeadLock();

        Thread t = new Thread(task);
        Thread t2 = new Thread(task);

        t.start();  // 开启Thread-0 线程
        /**
         * 在开启Thread-0之后，需要将flag修改为false，目的是让Thread-1进入run方法中的else中
         * 在执行t.start()代码的时候是主线程在执行，也就是cpu在主线程上，虽然开启Thread-0，但cpu不一定会立刻切换到Thread-0线程上
         * 如果cpu没有切换，那么cpu还在主线程上，就会执行 task.flag = false;结果就会将标记修改为false，
         * 修改结束之后，不管cpu会不会切换，这时标记都是false，即使cpu切换到thread-0线程上，也只能进入else中。
         * 所以我们需要让主线程在执行完t.start();之后休眠，目的是让cpu一定可以切换到thread-0线程上。
         */
        Thread.sleep(1);
        task.flag = false;
        t2.start();

    }
}
