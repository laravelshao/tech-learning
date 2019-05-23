package com.laravelshao.learning.concurrent.ticket;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class TicketTest {

    public static void main(String[] args) {
        /**
         * 电影院售票
         */
        
        // 创建实现类的对象
        Ticket task = new Ticket();

        // 创建线程
        Thread t = new Thread( task );
        Thread t2 = new Thread( task );
        Thread t3 = new Thread( task );
        Thread t4 = new Thread( task );

        // 开启线程
        t.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
