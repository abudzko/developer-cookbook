package budzko.zoo.request.service;

import budzko.zoo.request.kafka.procuder.AsyncRequest;
import budzko.zoo.request.kafka.procuder.RequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final RequestProducer requestProducer;

    public void sendRequest(String userId) {
        AsyncRequest asyncRequest = new AsyncRequest(userId);
        requestProducer.sendRequest(asyncRequest);
    }
}
