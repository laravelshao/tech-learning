package com.laravelshao.learning.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author shaoqinghua
 * @date 2018/9/19
 * @description
 */
public class ThreadStatus {

    public static void main(String[] args) {
        // TIME_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "timewaiting").start();

        // WAITING
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waiting").start();

        new Thread(new BlockedDemo(), "BlockDemo-01").start();
        new Thread(new BlockedDemo(), "BlockDemo-02").start();


    }

    static class BlockedDemo extends Thread {
        public void run() {
            synchronized (BlockedDemo.class) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


