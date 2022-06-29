package com.laravelshao.learning.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 测试
 *
 * @author qinghua.shao
 * @date 2022/6/28
 * @since 1.0.0
 */
public class AtomicIntegerTest {

    static Integer i = 0;
    static AtomicInteger j = new AtomicInteger(0);

    public static void main(String[] args) {
//		synchronizedAdd();
        atomicAdd();
    }

    /**
     * 同步代码块原子自增
     */
    private static void synchronizedAdd() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    // 10个线程就要依次的慢慢的一个一个的进入锁代码块
                    // 然后依次对i变量进行++的操作
                    // 每次操作完i++，就写回到主存，下一个线程间进行从主存来加载，再次i++
                    synchronized (AtomicIntegerTest.class) {
                        System.out.println(++AtomicIntegerTest.i);
                    }
                }
            }.start();
        }
    }

    /**
     * atomic 保证原子自增
     */
    private static void atomicAdd() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    System.out.println(AtomicIntegerTest.j.incrementAndGet());
                }
            }.start();
        }
    }

}
