package com.budzko.cookbook.websockets.client.standard;

import com.budzko.cookbook.websockets.client.model.Message;
import com.budzko.cookbook.websockets.client.model.OnMessageListener;
import com.budzko.cookbook.websockets.client.model.WebSocketConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class StandardWebSocketConnection implements WebSocketConnection<Message> {
    private final StandardWebSocketClient webSocketClient;
    private final String webSocketUrl;
    private final List<OnMessageListener<Message>> onMessageListeners;
    private WebSocketSession webSocketSession;

    public StandardWebSocketConnection(
            StandardWebSocketClient webSocketClient,
            String webSocketUrl
    ) {
        this.webSocketClient = webSocketClient;
        this.webSocketUrl = webSocketUrl;
        onMessageListeners = new LinkedList<>();
    }

    @Override
    public void establishConnection() throws ExecutionException, InterruptedException {
        CompletableFuture<WebSocketSession> completableFuture = webSocketClient.execute(
                new SocketHandler(),
                this.webSocketUrl
        );
        completableFuture.get();
    }

    @Override
    public void send(Message message) {
        byte[] bytes = message.getData().getBytes(StandardCharsets.UTF_8);
        BinaryMessage binaryMessage = new BinaryMessage(ByteBuffer.wrap(bytes));
        try {
            webSocketSession.sendMessage(binaryMessage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void addOnMessageListener(OnMessageListener<Message> onMessageListener) {
        onMessageListeners.add(onMessageListener);
    }

    class SocketHandler implements WebSocketHandler {

        @Override
        public void afterConnectionEstablished(WebSocketSession session) {
            webSocketSession = session;
        }

        @Override
        public void handleMessage(
                WebSocketSession session,
                WebSocketMessage<?> message
        ) {
            Message msg = new Message();
            msg.setData(new String(((ByteBuffer) message.getPayload()).array()));
            msg.setSenderId(session.getId());
            onMessageListeners.forEach(listener -> listener.onMessage(msg));
        }

        @Override
        public void handleTransportError(
                WebSocketSession session,
                Throwable exception
        ) {
            log.error(exception.getMessage(), exception);
        }

        @Override
        public void afterConnectionClosed(
                WebSocketSession session,
                CloseStatus closeStatus
        ) {
            log.info("afterConnectionClosed: %s %s".formatted(session.getId(), closeStatus));
        }

        @Override
        public boolean supportsPartialMessages() {
            return false;
        }
    }
}
