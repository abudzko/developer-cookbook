package com.budzko.cookbook.websockets.client.stomp;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Set;

@Configuration
@Getter
public class WebSocketStompConfig {
    @Value("${websocket.stomp.url}")
    private String webSocketUrl;
    @Value("${websocket.stomp.path}")
    private String webSocketStompPath;
    @Value("${websocket.stomp.subscriptions}")
    private Set<String> webSocketStompSubscriptions;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public WebSocketStompConnection webSocketStompConnection() {
        return new WebSocketStompConnection(
                webSocketStompClient(),
                webSocketUrl,
                webSocketStompPath,
                webSocketStompSubscriptions
        );
    }

    private WebSocketStompClient webSocketStompClient() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }
}
