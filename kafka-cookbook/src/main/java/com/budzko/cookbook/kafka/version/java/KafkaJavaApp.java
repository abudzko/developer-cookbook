package com.budzko.cookbook.kafka.version.java;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaJavaApp {

    public static final String LOCALHOST_9092 = "localhost:10001";

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
//        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
//        properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/home/aliaksei/Downloads/tmp/server.keystore.jks");
//        properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,  "password");
//
//// configure the following three settings for SSL Authentication
//        properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/home/aliaksei/Downloads/tmp/server.keystore.jks");
//        properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "password");
//        properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "password");
//        ssl.keystore.location
//        ssl.keystore.password
//        ssl.truststore.location
//        ssl.truststore.password
        Producer<String, String> producer = new KafkaProducer<>(properties);
        int i = 0;
        while (true) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<>("in", "Msg" + i++));
            System.out.println(future.get());
            System.out.println("sent");
            Thread.sleep(2000);
        }
    }
}
