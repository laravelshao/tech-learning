package com.laravelshao.learning.question.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 实现单生产者-单消费者模型，生产者以10个/分钟的频率生产商品；消费者以15个/分钟的频率消费商品，无商品可消费时等待2分钟
 */
public class ProducerConsumer {
    private static final int QUEUE_CAPACITY = 100;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                int item = 0;
                while (true) {
                    queue.put(item); // 生产商品
                    System.out.println("Produced: " + item);
                    item++;
                    TimeUnit.SECONDS.sleep(6); // 10个/分钟，即每6秒生产一个
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    //Integer item = queue.poll(2, TimeUnit.MINUTES); // 等待2分钟
                    Integer item = queue.poll(1, TimeUnit.SECONDS); // 修改为1秒复现无商品时等待过程
                    if (item != null) {
                        System.out.println("Consumed: " + item);
                        TimeUnit.SECONDS.sleep(4); // 15个/分钟，即每4秒消费一个
                    } else {
                        System.out.println("No item to consume, waiting...");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        }
    }
}