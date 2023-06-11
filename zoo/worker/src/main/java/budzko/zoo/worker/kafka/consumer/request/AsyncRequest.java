package budzko.zoo.worker.kafka.consumer.request;

import lombok.Data;

@Data
public class AsyncRequest {
    private String userId;
    private String partitionId;
}
