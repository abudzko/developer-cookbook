package com.budzko.cookbook.websockets.client.model;

import java.util.concurrent.ExecutionException;

public interface WebSocketConnection<T> {
    void establishConnection() throws ExecutionException, InterruptedException;

    void send(T message);

    void addOnMessageListener(OnMessageListener<T> onMessageListener);
}
