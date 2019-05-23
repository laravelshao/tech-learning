package com.laravelshao.learning.rabbitmq.advanced.tx;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者 测试事务模式(发送消息效率比较低，建议使用Confirm模式)
 * 参考文章：https://www.cnblogs.com/vipstone/p/9350075.html
 *
 * @author shaoqinghua
 * @date 2019/1/4
 * @description
 */
public class TransactionProducer {
    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        String msg = "Hello world, Rabbit MQ";
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        try {
            channel.txSelect();
            // 发送消息
            // String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish("", QUEUE_NAME, null, (msg).getBytes());
            // int i =1/0;
            channel.txCommit();
            System.out.println("消息发送成功");
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("消息已经回滚");
        }

        channel.close();
        connection.close();
    }
}

