package com.laravelshao.learning.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 测试
 * <p>
 * LockSupport#park()：挂起当前线程并阻塞
 * LockSupport#unpark(thread1)：唤醒指定线程
 *
 * @author qinghua.shao
 * @date 2022/7/3
 * @since 1.0.0
 */
public class LockSupportTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread() {
            @Override
            public void run() {

                System.out.println("线程1挂起之前执行的操作");
                LockSupport.park();
                System.out.println("线程1被唤醒后执行的操作");

            }
        };
        thread1.start();

        new Thread() {
            @Override
            public void run() {

                for (int i = 1; i <= 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("等待 " + i + " 秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("尝试唤醒线程1");
                LockSupport.unpark(thread1);
            }
        }.start();
    }
}
