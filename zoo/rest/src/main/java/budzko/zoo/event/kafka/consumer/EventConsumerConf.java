package budzko.zoo.event.kafka.consumer;

import budzko.zoo.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConf {
    private static final String EVENT_CONSUMER_GROUP_ID = "zoo-event-consumer-group-id";
    private final KafkaConfig config;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> eventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventConsumerFactory());
        return factory;
    }

    private ConsumerFactory<String, Map<String, String>> eventConsumerFactory() {
        Map<String, Object> props = config.createConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, EVENT_CONSUMER_GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
