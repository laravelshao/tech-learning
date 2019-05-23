package com.laravelshao.learning.rabbitmq.advanced.confirm;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 消息生产者 测试普通确认模式
 */
public class NormalConfirmProducer {

    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        String msg = "Hello world, Rabbit MQ ,Normal Confirm";
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 开启发送方确认模式
        channel.confirmSelect();

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        // 普通Confirm，发送一条，确认一条
        if (channel.waitForConfirms()) {
            System.out.println("消息发送成功");
        }

        channel.close();
        connection.close();
    }
}
