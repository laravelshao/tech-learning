package com.laravelshao.learning.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程优雅终止
 *
 * <ul>
 * <li>{@link Thread#interrupt} 结合 {@link Thread#isInterrupted}</li>
 * <li>使用 {@code volatile} 修饰的终止标识中断</li>
 * </ul>
 *
 * @author shaoqinghua
 * @date 2018/9/19
 * @description
 */
public class InterruptDemo {

    public static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean flag = Thread.currentThread().isInterrupted();
                if (true) {
                    System.out.println("before:" + flag);
                    Thread.interrupted(); // 对线程复位 中断标识为false
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); // 设置中断标识 中断标识为true
    }
}
