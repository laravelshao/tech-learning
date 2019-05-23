package com.laravelshao.learning.concurrent.waitnotify;

/**
 * Created by shaoqinghua on 2018/5/3.
 * 测试单生产单消费
 */
public class ThreadTest {
    public static void main(String[] args) {
        //创建资源对象
        Resource r = new Resource();

        //创建线程任务对象
        Productor pro = new Productor(r);
        Consumer con = new Consumer(r);

        //创建生产和消费的线程对象
        Thread t = new Thread(pro);
        Thread t2 = new Thread(con);

        //启动线程
        t.start();
        t2.start();
    }
}
