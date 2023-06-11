package budzko.zoo.worker.kafka.consumer.request;

import budzko.zoo.worker.kafka.consumer.request.AsyncRequest;
import budzko.zoo.worker.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestConsumer {

    private final ResponseService responseService;

    @KafkaListener(
            topics = "${kafka.topic.request-topic-name}",
            containerFactory = "requestKafkaListenerContainerFactory",
            autoStartup = "true",
            clientIdPrefix = "${kafka.consumer.request.client-prefix-id}"
    )
    public void listen(
            @Payload AsyncRequest asyncRequest,
            @Headers MessageHeaders headers
    ) {
        try {
            log.info("Received message: %s. %s".formatted(asyncRequest, headers));
            responseService.sendResponse(asyncRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
