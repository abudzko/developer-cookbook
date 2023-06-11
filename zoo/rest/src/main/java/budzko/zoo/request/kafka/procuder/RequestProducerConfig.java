package budzko.zoo.request.kafka.procuder;

import budzko.zoo.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class RequestProducerConfig {
    private final KafkaConfig config;

    @Bean
    public KafkaTemplate<String, AsyncRequest> sendRequetKafkaTemplate() {
        Map<String, Object> properties = config.createProducerProperties();
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        DefaultKafkaProducerFactory<String, AsyncRequest> factory
                = new DefaultKafkaProducerFactory<>(properties);
        return new KafkaTemplate<>(factory);
    }
}
