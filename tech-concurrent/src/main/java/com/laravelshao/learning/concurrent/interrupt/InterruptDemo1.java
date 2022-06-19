package com.laravelshao.learning.concurrent.interrupt;

/**
 * Interrupt 样例1：interrupt() 结合 isInterrupted() 中断线程执行
 *
 * <p> interrupt() 方法本质上不是直接中断线程执行，而是设置了中断状态
 *
 * @author qinghua.shao
 * @date 2022/6/19
 * @since 1.0.0
 */
public class InterruptDemo1 {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    System.out.println("线程任务在执行......");
                }
            }
        };

        thread.start();

        Thread.sleep(1000L);

        // 设置线程打断，本质是修改了中断状态，并不是直接打断执行，需要结合 isInterrupted() 配合使用
        thread.interrupt();

    }
}
