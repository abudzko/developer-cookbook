package budzko.zoo.event.kafka.producer;

import budzko.zoo.kafka.listener.PartitionReadinessListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static budzko.zoo.kafka.Utils.HEADER;
import static budzko.zoo.kafka.Utils.HEADER_VALUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, String> sendEventKafkaTemplate;
    private final PartitionReadinessListener readinessListener;
    @Value(value = "${kafka.topic.event-topic-name}")
    private String eventTopicName;

    public void sendEvent(String event) {
        if(readinessListener.isReady()) {
            sendEvent(event, sendEventKafkaTemplate);
        } else {
            log.warn("Can't send event. Kafka is not ready");
        }
    }

    public <T> void sendEvent(T event, KafkaTemplate<String, T> kafkaTemplate) {
        var producerRecord = new ProducerRecord<String, T>(eventTopicName, null, event);
        producerRecord.headers().add(new RecordHeader(HEADER, HEADER_VALUE));
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
