package budzko.zoo.worker.kafka.producer.response;

import budzko.zoo.worker.kafka.producer.response.AsyncResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseProducer {

    private final KafkaTemplate<String, AsyncResponse> sendResponseKafkaTemplate;
    @Value(value = "${kafka.topic.response-topic-name}")
    private String responseTopicName;

    public void sendResponse(AsyncResponse response) {
        sendResponse(response, sendResponseKafkaTemplate);
    }

    public <T extends AsyncResponse> void sendResponse(T response, KafkaTemplate<String, T> kafkaTemplate) {
        var producerRecord = new ProducerRecord<String, T>(
                responseTopicName,
                Integer.valueOf(response.getPartitionId()),
                null,
                response
        );
        CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(producerRecord);
        future.whenCompleteAsync((result, throwable) -> {
            if (throwable != null) {
                log.error(throwable.getMessage(), throwable);
            } else {
                log.debug(result.toString());
            }
        });
    }
}
