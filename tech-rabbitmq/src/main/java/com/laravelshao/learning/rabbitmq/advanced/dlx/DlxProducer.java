package com.laravelshao.learning.rabbitmq.advanced.dlx;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.AMQBasicProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者 测试死信队列
 *
 * @author shaoqinghua
 * @date 2019/1/4
 * @description
 */
public class DlxProducer {

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        // 设置属性 消息过期时间10秒
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") // TTL
                .build();

        // 发送消息
        for (int i = 0; i < 10; i++) {
            String msg = "dlx msg-" + i;
            channel.basicPublish("", "PRE_DLX_QUEUE", properties, msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}

