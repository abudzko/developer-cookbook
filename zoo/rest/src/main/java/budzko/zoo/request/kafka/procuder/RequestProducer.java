package budzko.zoo.request.kafka.procuder;

import budzko.zoo.kafka.listener.PartitionReadinessListener;
import budzko.zoo.request.kafka.consumer.response.ResponseConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestProducer {
    private final KafkaTemplate<String, AsyncRequest> sendRequetKafkaTemplate;
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final PartitionReadinessListener readinessListener;

    @Value(value = "${kafka.topic.request-topic-name}")
    private String requestTopicName;

    private static String getConsumerId() {
        return ResponseConsumer.class.getSimpleName();
    }

    public void sendRequest(AsyncRequest request) {
        if (isReady()) {
            request.setPartitionId(getAssignedPartitionId());
            sendRequest(request, sendRequetKafkaTemplate);
        } else {
            log.warn(
                    """
                            Ignored.
                            Consumer is not ready. [%s]
                            """.formatted(request)
            );
        }
    }

    public <T> void sendRequest(T request, KafkaTemplate<String, T> kafkaTemplate) {
        var producerRecord = new ProducerRecord<String, T>(requestTopicName, null, request);
        CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(producerRecord);
        future.whenCompleteAsync((result, throwable) -> {
            if (throwable != null) {
                log.error(throwable.getMessage(), throwable);
            } else {
                log.debug(result.toString());
            }
        });
    }

    private String getAssignedPartitionId() {
        String consumerId = getConsumerId();
        MessageListenerContainer container = getMessageListenerContainer(consumerId);
        Integer partitionId = Objects.requireNonNull(container.getAssignedPartitions()).stream()
                .findFirst()
                .map(TopicPartition::partition)
                .orElseThrow(() -> new IllegalStateException("No one partition for consumer %s".formatted(consumerId)));
        return String.valueOf(partitionId);
    }

    private MessageListenerContainer getMessageListenerContainer(String consumerId) {
        MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(consumerId);
        if (container == null) {
            throw new IllegalStateException("Can't find consumer %s".formatted(consumerId));
        }
        return container;
    }

    private boolean isReady() {
        return readinessListener.isReady();
    }
}
