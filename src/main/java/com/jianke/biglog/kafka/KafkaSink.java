package com.jianke.biglog.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaSink {

    public static void main (String[] args) {
        System.out.println("begin consumer");
        connectKafka();
        System.out.println("finish consumer");
    }

    public static void connectKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.16.20.41:9092,172.16.20.42:9092,172.16.20.43:9092");
        props.put("group.id", "testConsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("biglog"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("===================offset = %d, key = %s, value = %s", record.offset(), record.key(),
                        record.value());
            }
        }
    }
}
