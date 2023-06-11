package budzko.zoo.event.kafka.producer;

import budzko.zoo.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class EventProducerConfig {
    private final KafkaConfig config;

    @Bean
    public KafkaTemplate<String, String> sendEventKafkaTemplate() {
        Map<String, Object> properties = config.createProducerProperties();
        DefaultKafkaProducerFactory<String, String> factory
                = new DefaultKafkaProducerFactory<>(properties);
        return new KafkaTemplate<>(factory);
    }
}
