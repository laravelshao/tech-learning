package com.laravelshao.learning.rabbitmq.advanced.returnlistener;

import com.laravelshao.learning.rabbitmq.util.MQConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoqinghua
 * @date 2019/1/4
 * @description 消息无法匹配到队列时会发回给生产者
 */
public class ReturnListenerProducer {
    public static void main(String[] args) throws Exception {

        // 获取连接
        Connection connection = MQConnectUtil.getRabbitMQConnection();
        // 创建消息通道
        Channel channel = connection.createChannel();

        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode,
                                     String replyText,
                                     String exchange,
                                     String routingKey,
                                     AMQP.BasicProperties properties,
                                     byte[] body)
                    throws IOException {
                System.out.println("=========监听器收到了无法路由，被返回的消息============");
                System.out.println("replyText:" + replyText);
                System.out.println("exchange:" + exchange);
                System.out.println("routingKey:" + routingKey);
                System.out.println("message:" + new String(body));
            }
        });

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

        // 声明交换机时设置备份交换机
        Map<String, Object> arguments = new HashMap<>(16);
        arguments.put("alternate-exchange", "ALTERNATE_EXCHANGE");
        channel.exchangeDeclare("TEST_EXCHANGE", "topic", false, false, false, arguments);

        // 发送至默认交换机，由于没有任何队列使用这个关键字绑定交换机，所以会被退回
        // 第三个参数mandatory，如果mandatory是false，消息也会被直接丢弃
        // String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body
        channel.basicPublish("", "hahahaha", true, properties, "哈哈哈".getBytes());

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();
    }
}
