package budzko.zoo.kafka.listener;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class PartitionReadinessListener implements ConsumerRebalanceListener {
    @Getter
    private volatile boolean isReady;

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        log.info("onPartitionsRevoked: %s".formatted(partitions));
        notRead();
    }

    private void notRead() {
        isReady = false;
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        log.info("onPartitionsAssigned: %s".formatted(partitions));
        ready();
    }

    private void ready() {
        isReady = true;
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        onPartitionsRevoked(partitions);
    }
}
