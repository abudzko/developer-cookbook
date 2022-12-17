package com.budzko.cookbook.kafka.system.java;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaJavaApp {

    public static final String LOCALHOST_9092 = "localhost:9092";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                LOCALHOST_9092
        );
        properties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
        properties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
        properties.put(
                ProducerConfig.BATCH_SIZE_CONFIG,
                1
        );
        Producer<String, String> producer = new KafkaProducer<>(properties);
        int i = 0;
        while (true) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<>("TOPIC_1", "Msg" + i++));
            System.out.println(future.get());
            System.out.println("sent");
            Thread.sleep(2000);
        }
    }
}
