package com.laravelshao.learning.rabbitmq.type.simple;


import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 简单队列发送者
 */
public class Sender {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}