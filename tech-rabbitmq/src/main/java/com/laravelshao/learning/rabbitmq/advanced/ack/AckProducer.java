package com.laravelshao.learning.rabbitmq.advanced.ack;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 消息生产者 测试消费者手工应答和重回队列
 */
public class AckProducer {

    private final static String QUEUE_NAME = "ACK_QUEUE";

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        String msg = "ack message";
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送消息
        // String exchange, String routingKey, BasicProperties props, byte[] body
        for (int i = 0; i < 5; i++) {
            channel.basicPublish("", QUEUE_NAME, null, (msg + i).getBytes());
        }

        channel.close();
        connection.close();
    }
}

