package com.budzko.cookbook.websockets.client.stomp;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Set;

@Configuration
@Getter
public class WebSocketConfig {

    private final String webSocketStompUrl = "ws://localhost:8080/webapp";
    private final String webSocketStompPath = "/app/message";
    private final Set<String> webSocketStompSubscriptions = Set.of("/topic/pong");

    @Bean
    public WebSocketStompClient webSocketStompClient() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }
}
