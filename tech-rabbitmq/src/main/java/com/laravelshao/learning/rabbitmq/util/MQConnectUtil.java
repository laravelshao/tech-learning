package com.laravelshao.learning.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description MQ连接工具
 */
public class MQConnectUtil {

    /**
     * 获取RabbitMQ连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getRabbitMQConnection() throws Exception {

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 服务地址
        factory.setHost("localhost");
        // 端口
        factory.setPort(5672);

        // 设置账号信息及VirtualHost
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        // 获取连接
        return factory.newConnection();
    }
}
