package com.budzko.cookbook.kafka.system.spring.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    public static final String TOPIC = "TOPIC_1";

    @KafkaListener(topics = TOPIC)
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
