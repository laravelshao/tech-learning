package com.laravelshao.learning.rabbitmq.type.work;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description Work队列消费者
 */
public class Receiver2 {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x] Received '" + message + "'");
                try {
                    // 休眠 模拟不同消费速度
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 设置确认状态
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 监听队列手动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}