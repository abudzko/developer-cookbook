package com.budzko.cookbook.kafka.version.spring.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, String> eventKafkaTemplate;
    @Value("${spring.kafka.topics.log-event}")
    private String eventTopic;
    private long messageSendInterval = 10000L;

    private static String buildMessage() {
        return LocalDateTime.now() + " " + UUID.randomUUID();
    }

    @PostConstruct
    void init() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = buildMessage();
                    eventKafkaTemplate.send(eventTopic, message);
                    log.info("Message was send: {}", message);
                    Thread.sleep(messageSendInterval);
                }
            } catch (RuntimeException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }).start();
    }
}
