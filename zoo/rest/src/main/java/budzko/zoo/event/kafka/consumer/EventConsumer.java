package budzko.zoo.event.kafka.consumer;

import budzko.zoo.kafka.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static budzko.zoo.kafka.Utils.HEADER;

@Service
@Slf4j
public class EventConsumer {

    @KafkaListener(
            id = "EventConsumer",
            topics = "${kafka.topic.event-topic-name}",
            containerFactory = "eventKafkaListenerContainerFactory",
            autoStartup = "true",
            clientIdPrefix = "zoo",
            concurrency ="3"
    )
    public void listen(
            @Payload String message,
            @Headers MessageHeaders headers
    ) {
        try {
            log.info("Received message: %s. %s".formatted(message, Utils.readHeader(headers, HEADER)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
