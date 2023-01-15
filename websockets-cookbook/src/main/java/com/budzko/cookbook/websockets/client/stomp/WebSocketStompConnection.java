package com.budzko.cookbook.websockets.client.stomp;

import com.budzko.cookbook.websockets.client.model.Message;
import com.budzko.cookbook.websockets.client.model.OnMessageListener;
import com.budzko.cookbook.websockets.client.model.WebSocketConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WebSocketStompConnection extends StompSessionHandlerAdapter implements WebSocketConnection<Message> {

    private final String webSocketStompUrl;
    private final String webSocketStompPath;
    private final Set<String> webSocketStompSubscriptions;
    private final WebSocketStompClient webSocketStompClient;

    private StompSession stompSession;
    private final List<OnMessageListener<Message>> onMessageListeners;

    public WebSocketStompConnection(WebSocketConfig webSocketConfig) {
        this.webSocketStompClient = webSocketConfig.webSocketStompClient();
        this.webSocketStompSubscriptions = webSocketConfig.getWebSocketStompSubscriptions();
        this.webSocketStompUrl = webSocketConfig.getWebSocketStompUrl();
        this.webSocketStompPath = webSocketConfig.getWebSocketStompPath();
        onMessageListeners = new LinkedList<>();
    }

    @Override
    public void afterConnected(
            StompSession session,
            StompHeaders connectedHeaders
    ) {
        log.info("Connection established: " + session.getSessionId());
        this.stompSession = session;
        this.webSocketStompSubscriptions.forEach(
                subscription -> stompSession.subscribe(subscription, this));
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message message = (Message) payload;
        onMessageListeners.forEach(listener -> listener.onMessage(message));
    }

    @Override
    public void handleException(
            StompSession session,
            StompCommand command,
            StompHeaders headers,
            byte[] payload,
            Throwable ex
    ) {
        log.error(ex.getMessage(), ex);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void establishConnection() throws ExecutionException, InterruptedException {
        this.webSocketStompClient.connectAsync(webSocketStompUrl, this).get();
    }

    @Override
    public void send(Message message) {
        Objects.requireNonNull(stompSession, "Websocket connection wasn't established");
        stompSession.send(webSocketStompPath, message);
    }

    @Override
    public void addOnMessageListener(OnMessageListener<Message> onMessageListener) {
        onMessageListeners.add(onMessageListener);
    }
}
