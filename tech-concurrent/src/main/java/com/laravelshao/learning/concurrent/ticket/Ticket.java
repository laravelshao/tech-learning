package com.laravelshao.learning.concurrent.ticket;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class Ticket implements Runnable {

    //总票数
    private int num = 100;

    //定义一个成员变量，充当同步上的锁对象
    private Object lock = new Object();
    
    @Override
    public void run(){
        while( true ){
            // 需要在if的外面添加同步代码块
            //  t1  t2  t3
            // 任何线程进入同步代码块之前，需要在这里获取到锁对象（隐式获取锁）
            synchronized( lock ){
                // 保证num大于零的时候可以售票
                if( num > 0 ){
                    System.out.println(Thread.currentThread().getName() +" 正在售出的票号："+ num);
                    // t0
                    num--;
                }
            }
            //在线程出同步的时候，这个线程会将持有的锁释放
        }
    }

}
