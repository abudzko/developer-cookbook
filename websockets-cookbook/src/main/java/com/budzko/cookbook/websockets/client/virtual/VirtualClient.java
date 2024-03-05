package com.budzko.cookbook.websockets.client.virtual;

import com.budzko.cookbook.websockets.client.model.Message;
import com.budzko.cookbook.websockets.client.model.OnMessageListener;
import com.budzko.cookbook.websockets.client.model.WebSocketConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class VirtualClient implements OnMessageListener<Message> {
    private final WebSocketConnection<Message> webSocketConnection;
    private final int messageToSendCount;
    private final int intervalBetweenMessagesSec;

    public VirtualClient(
            WebSocketConnection<Message> webSocketConnection,
            int messageToSendCount,
            int intervalBetweenMessagesSec
    ) {
        this.webSocketConnection = webSocketConnection;
        this.messageToSendCount = messageToSendCount;
        this.intervalBetweenMessagesSec = intervalBetweenMessagesSec;
        webSocketConnection.addOnMessageListener(this);
    }

    public void start() {
        try {
            webSocketConnection.establishConnection();
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Can't establish connection");
            return;
        }
        new Thread(this::sendMessage).start();
    }

    public void sendMessage() {
        int sentCount = 0;
        while (sentCount < messageToSendCount) {
            sentCount++;
            webSocketConnection.send(Message.generateRandomMessage());
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(intervalBetweenMessagesSec));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(e.getMessage(), e);
            }
        }
        try {
            new LinkedBlockingDeque<>().take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessage(Message message) {
        log.info("Received: {}", message);
    }
}
