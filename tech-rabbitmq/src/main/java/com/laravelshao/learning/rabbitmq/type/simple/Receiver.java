package com.laravelshao.learning.rabbitmq.type.simple;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.*;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 简单队列消费者
 */
public class Receiver {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body);
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        // 监听队列自动确认(true)
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}