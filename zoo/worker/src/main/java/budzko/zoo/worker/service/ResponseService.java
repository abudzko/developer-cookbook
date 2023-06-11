package budzko.zoo.worker.service;

import budzko.zoo.worker.kafka.consumer.request.AsyncRequest;
import budzko.zoo.worker.kafka.producer.response.ResponseProducer;
import budzko.zoo.worker.kafka.producer.response.AsyncResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final ResponseProducer responseProducer;

    public void sendResponse(AsyncRequest request) {
        AsyncResponse asyncResponse = new AsyncResponse();
        asyncResponse.setUserId(request.getUserId());
        asyncResponse.setId(UUID.randomUUID().toString());
        asyncResponse.setPartitionId(request.getPartitionId());
        responseProducer.sendResponse(asyncResponse);
    }
}
