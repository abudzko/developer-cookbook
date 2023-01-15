package com.budzko.cookbook.websockets.client.virtual;

import com.budzko.cookbook.websockets.client.stomp.WebSocketConfig;
import com.budzko.cookbook.websockets.client.stomp.WebSocketStompConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@RequiredArgsConstructor
public class VirtualClientFactory {
    private final WebSocketConfig webSocketConfig;

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public VirtualClient createStompVirtualClient() {
        WebSocketStompConnection connection = new WebSocketStompConnection(webSocketConfig);
        return new VirtualClient(connection);
    }
}
