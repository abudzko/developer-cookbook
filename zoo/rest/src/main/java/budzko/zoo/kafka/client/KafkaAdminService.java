package budzko.zoo.kafka.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaAdminService {

    private final AdminClient adminClient;

    public CompletableFuture<Set<String>> topics() {
        var future = new CompletableFuture<Set<String>>();
        KafkaFuture<Set<String>> kafkaFuture = adminClient.listTopics().names();
        kafkaFuture.whenComplete((topics, throwable) -> {
            if (throwable != null) {
                log.error(throwable.getMessage(), throwable);
            } else {
                future.complete(topics);
            }
        });
        return future;
    }

    public CompletableFuture<Set<String>> createTopic(String topic) {
        List<NewTopic> newTopics = List.of(new NewTopic(topic, 1, (short) 1));
        adminClient.createTopics(newTopics);
        return topics();
    }

    public CompletableFuture<Set<String>> deleteTopic(String topic) {
        adminClient.deleteTopics(Set.of(topic));
        return topics();
    }
}
