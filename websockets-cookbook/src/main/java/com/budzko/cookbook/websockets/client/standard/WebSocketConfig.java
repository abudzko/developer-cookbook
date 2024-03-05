package com.budzko.cookbook.websockets.client.standard;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
@Getter
public class WebSocketConfig {
    @Value("${websocket.standard.url}")
    private String webSocketUrl;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public StandardWebSocketConnection webSocketStandardConnection() {
        return new StandardWebSocketConnection(
                webSocketClient(),
                webSocketUrl
        );
    }

    private StandardWebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }
}
