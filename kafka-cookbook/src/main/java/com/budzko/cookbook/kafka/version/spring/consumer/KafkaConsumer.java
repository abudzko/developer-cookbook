package com.budzko.cookbook.kafka.version.spring.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @Value("${source}")
    private String source;

    @KafkaListener(
            topics = "${spring.kafka.topics.log-event}",
            containerFactory = "msgListenerContainerFactory",
            autoStartup = "true",
            clientIdPrefix = "msg"
    )
    public void listen(
            @Payload String message,
            @Headers MessageHeaders messageHeaders
    ) {
        try {
            if (source.equals(messageHeaders.get("source"))) {
                log.info("Message received: {}. {}", message, messageHeaders);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
