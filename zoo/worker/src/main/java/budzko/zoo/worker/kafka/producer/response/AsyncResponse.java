package budzko.zoo.worker.kafka.producer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AsyncResponse {
    @JsonProperty
    private String userId;
    @JsonProperty
    private String partitionId;
    @JsonProperty
    private String id;
}
