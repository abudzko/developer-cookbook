package budzko.zoo.request.kafka.consumer.response;

import budzko.zoo.kafka.KafkaConfig;
import budzko.zoo.kafka.listener.PartitionReadinessListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.time.Duration;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ResponseConsumerConf {
    private static final String RESPONSE_CONSUMER_GROUP_ID = "zoo-response-consumer-group-id";
    private final KafkaConfig config;
    private final PartitionReadinessListener partitionReadinessListener;
    @Value(value = "${kafka.consumer.request.start-timeout-sec:5}")
    private Long consumerStartTimeOutSec;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> responseKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(responseConsumerFactory());
        factory.setRecordMessageConverter(new StringJsonMessageConverter());
        factory.getContainerProperties().setConsumerStartTimeout(Duration.ofSeconds(consumerStartTimeOutSec));
        factory.getContainerProperties().setConsumerRebalanceListener(partitionReadinessListener);
        return factory;
    }

    private ConsumerFactory<String, Map<String, String>> responseConsumerFactory() {
        Map<String, Object> props = config.createConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, RESPONSE_CONSUMER_GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
