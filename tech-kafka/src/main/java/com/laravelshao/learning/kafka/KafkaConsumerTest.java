package com.laravelshao.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author shaoqinghua
 * @date 2018/11/5
 * @description kafka消费者测试
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        /**
         * 具体配置选项参考 ConsumerConfig
         *
         * group.id
         *      同一组的消费者存在竞争关系，不同组的消费者可以消费相同的消息
         *
         * enable.auto.commit
         *      消费者消费消息后自动提交，只有当消息提交以后，该消息才不会被再次接收到
         *
         * auto.offset.reset
         *      针对新groupId消费者消费执行topic主题
         *      latest：新的消费者将会从其他消费者最后消费的offset处开始消费Topic下的消息
         *      earliest：新的消费者会从该topic最早的消息开始消费
         *      none：新的消费者加入以后，由于之前不存在offset，则会直接抛出异常
         *
         * max.poll.records
         *      设置每次poll返回消息数，可以预测每次poll间隔要处理的最大值。通过调整此值，可以减少poll间隔
         *
         * enable.auto.commit
         *      是否开启自动提交(默认为true)
         *
         * auto.commit.interval.ms
         *      开启自动提交后提交时间间隔(默认5000)
         */
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.105.128:9092");
        props.put("group.id", "KafkaConsumerTest-Group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");

        String topic = "test";
        KafkaConsumer kafkaConsumer = new KafkaConsumer(props);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            for (ConsumerRecord record : consumerRecords) {
                System.out.println("message receive: " + record.value());
            }
        }
    }
}
