package budzko.zoo.worker.kafka.consumer.request;

import budzko.zoo.worker.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RequestConsumerConf {
    private final KafkaConfig config;

    @Value("${kafka.consumer.request.group-id:}")
    private String requestConsumerGroupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> requestKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String, String>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(requestConsumerFactory());
        factory.setRecordMessageConverter(new StringJsonMessageConverter());
        return factory;
    }

    private ConsumerFactory<String, Map<String, String>> requestConsumerFactory() {
        Map<String, Object> props = config.createConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, requestConsumerGroupId);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
