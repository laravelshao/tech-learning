package com.laravelshao.learning.question.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 实现单生产者-单消费者模型，生产者以10个/分钟的频率生产商品；消费者以15个/分钟的频率消费商品，无商品可消费时等待2分钟
 */
public class ProducerConsumer {

    private static final BlockingQueue<String> QUEUE = new ArrayBlockingQueue<>(100);

    static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                int itemCnt = 1;
                while (true) {
                    // 生产间隔：60秒 / 10个 = 6秒/个
                    Thread.sleep(6000);

                    String item = "商品-" + (itemCnt++);
                    QUEUE.offer(item);
                    System.out.println("生产: " + item + " | 队列大小: " + QUEUE.size());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    // 消费间隔：60秒 / 15个 = 4秒/个
                    Thread.sleep(4000);

                    String item = QUEUE.poll();
                    if (item != null) {
                        System.out.println("消费: " + item + " | 队列大小: " + QUEUE.size());
                    } else {
                        System.out.println("无商品可消费等待2分钟...");
                        Thread.sleep(30 * 1000);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}