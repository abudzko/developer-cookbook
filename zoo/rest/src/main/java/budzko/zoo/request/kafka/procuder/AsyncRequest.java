package budzko.zoo.request.kafka.procuder;

import lombok.Data;

@Data
public class AsyncRequest {
    private final String userId;
    private String partitionId;
}
