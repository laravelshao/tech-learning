package com.laravelshao.learning.rabbitmq.advanced.ttl;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 消息生产者 测试TTL
 */
public class TTLProducer {

    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        // 通过队列属性设置消息过期时间
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("x-message-ttl", 6000); // 设置队列过期时间
        props.put("x-max-priority", 10); // 设置队列优先级

        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare("TTL_QUEUE", false, false, false, props);

        // 对每条消息设置过期时间
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") // TTL
                .priority(5) // 优先级(默认5) 配合队列 x-max-priority 属性使用
                .build();

        // 此处两种方式设置消息过期时间的方式都使用了，将以较小的数值为准

        // 发送消息
        String msg = "ttl msg";
        channel.basicPublish("", "TTL_QUEUE", properties, msg.getBytes());

        channel.close();
        connection.close();
    }
}

