package com.laravelshao.learning.concurrent.interrupt;

/**
 * Interrupt 样例2：interrupt() 中断休眠状态线程
 * <p>一般可用于线程优雅关闭
 * <p> interrupt() 方法本质上不是直接中断线程执行，而是设置了中断状态
 *
 * @author qinghua.shao
 * @date 2022/6/19
 * @since 1.0.0
 */
public class InterruptDemo2 {

    public static void main(String[] args) throws Exception {

        MyThread thread = new MyThread();
        thread.start();

        Thread.sleep(1000L);

        // 设置终止标识
        thread.setShouldRun(false);
        // 通过 interrupt() 中断休眠状态
        thread.interrupt();
    }

    /**
     * 线程任务
     */
    private static class MyThread extends Thread {

        private Boolean shouldRun = true;

        public void setShouldRun(Boolean shouldRun) {
            this.shouldRun = shouldRun;
        }

        @Override
        public void run() {
            while (shouldRun) {
                try {
                    System.out.println("线程1在执行工作......");
                    Thread.sleep(30 * 1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
