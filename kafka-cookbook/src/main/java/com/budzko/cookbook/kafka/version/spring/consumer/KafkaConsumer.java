package com.budzko.cookbook.kafka.version.spring.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topics = "${spring.kafka.topics.log-event}",
            containerFactory = "msgListenerContainerFactory",
            autoStartup = "true",
            clientIdPrefix = "msg"
    )
    public void listen(@Payload String message) {
        try {
            log.info("Message received: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
