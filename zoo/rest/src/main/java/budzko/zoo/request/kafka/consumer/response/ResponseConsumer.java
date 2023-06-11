package budzko.zoo.request.kafka.consumer.response;

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
public class ResponseConsumer {

    @KafkaListener(
            id = "ResponseConsumer",
            topics = "${kafka.topic.response-topic-name}",
            containerFactory = "responseKafkaListenerContainerFactory",
            autoStartup = "true",
            clientIdPrefix = "zoo-response"
    )
    public void listen(
            @Payload AsyncResponse response,
            @Headers MessageHeaders headers
    ) {
        try {
            log.info("Received response: %s. %s".formatted(response, Utils.readHeader(headers, HEADER)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}