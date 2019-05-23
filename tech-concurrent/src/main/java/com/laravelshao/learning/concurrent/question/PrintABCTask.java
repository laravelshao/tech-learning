package com.laravelshao.learning.concurrent.question;


/**
 * @author shaoqinghua
 * @date 2019/2/11
 * @description 三个线程交替打印ABC(synchronized实现)
 */
public class PrintABCTask implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private PrintABCTask(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {// 多线程并发，不能用if，必须使用while循环
            synchronized (prev) { // 先获取 prev 锁
                synchronized (self) {// 再获取 self 锁
                    System.out.println(Thread.currentThread().getName() + "->" + name);// 打印
                    count--;

                    self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                }
                // 此时执行完self的同步块，这时self锁才释放。
                try {
                    if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                        prev.notifyAll();
                    } else {
                        prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        PrintABCTask pa = new PrintABCTask("A", c, a);
        PrintABCTask pb = new PrintABCTask("B", a, b);
        PrintABCTask pc = new PrintABCTask("C", b, c);

        new Thread(pa, "线程A").start();
        Thread.sleep(10);// 保证初始ABC的启动顺序
        new Thread(pb, "线程B").start();
        Thread.sleep(10);
        new Thread(pc, "线程C").start();
        Thread.sleep(10);
    }
}
