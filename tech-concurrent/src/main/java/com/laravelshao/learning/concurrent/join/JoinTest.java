package com.laravelshao.learning.concurrent.join;

/**
 * 在 main 线程中开启了线程任务，且调用该线程的 join 方法，则会导致 main 线程阻塞，需要等待该线程任务执行完毕 main 线程才能继续往下执行。
 * <p>
 * 三个子线程 T1、T2、T3 任务，需要按照顺序执行，可以使用 join 方法实现
 *
 * @author qinghua.shao
 * @date 2023/9/9
 * @since 1.0.0
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("主线程开始执行......");

        Thread thread1 = new Thread(() -> {
            System.out.println("线程1开始执行......");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行完毕......");
        });

        thread1.start();
        thread1.join();


        Thread thread2 = new Thread(() -> {
            System.out.println("线程2开始执行......");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行完毕......");
        });

        thread2.start();
        thread2.join();


        Thread thread3 = new Thread(() -> {
            System.out.println("线程3开始执行......");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3执行完毕......");
        });

        thread3.start();
        thread3.join();


        System.out.println("主线程执行完毕......");
    }
}
