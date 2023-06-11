package budzko.zoo.worker.kafka.producer.response;

import budzko.zoo.worker.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

@RequiredArgsConstructor
@Configuration
public class ResponseProducerConfig {
    private final KafkaConfig config;

    @Bean
    public KafkaTemplate<String, AsyncResponse> sendResponseKafkaTemplate() {
        Map<String, Object> properties = config.createProducerProperties();
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        DefaultKafkaProducerFactory<String, AsyncResponse> factory
                = new DefaultKafkaProducerFactory<>(properties);
        return new KafkaTemplate<>(factory);
    }
}
