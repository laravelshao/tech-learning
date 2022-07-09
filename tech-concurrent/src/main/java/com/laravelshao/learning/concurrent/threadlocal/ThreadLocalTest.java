package com.laravelshao.learning.concurrent.threadlocal;

/**
 * ThreadLocal 测试
 *
 * @author qinghua.shao
 * @date 2018/7/9
 * @since 1.0.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<Long> reqId = new ThreadLocal<>();

        new Thread() {
            @Override
            public void run() {
                reqId.set(1L);
                System.out.println("线程1读取：" + reqId.get());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                reqId.set(2L);
                System.out.println("线程2读取：" + reqId.get());
            }
        }.start();
    }
}
