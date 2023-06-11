package budzko.zoo.rest;

import budzko.zoo.kafka.client.KafkaAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class KafkaInfoController {
    private final KafkaAdminService kafkaAdminService;

    @GetMapping("/topics")
    public CompletableFuture<Set<String>> topics() {
        return kafkaAdminService.topics();
    }

    @GetMapping("/createTopic")
    public CompletableFuture<Set<String>> createTopic(@RequestParam String topic) {
        return kafkaAdminService.createTopic(topic);
    }

    @GetMapping("/deleteTopic")
    public CompletableFuture<Set<String>> deleteTopic(@RequestParam String topic) {
        return kafkaAdminService.deleteTopic(topic);
    }
}
