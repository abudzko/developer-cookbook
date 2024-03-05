package com.budzko.cookbook.websockets.client.virtual;

import com.budzko.cookbook.websockets.client.standard.WebSocketConfig;
import com.budzko.cookbook.websockets.client.stomp.WebSocketStompConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
@RequiredArgsConstructor
public class VirtualClientFactory {

    private final WebSocketStompConfig webSocketStompConfig;
    private final WebSocketConfig webSocketConfig;
    @Value("${websocket.client.virtual.messageToSendCount}")
    private int messageToSendCount;
    @Value("${websocket.client.virtual.intervalBetweenMessagesSec}")
    private int intervalBetweenMessagesSec;

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public VirtualClient stompVirtualClient() {
        return new VirtualClient(
                webSocketStompConfig.webSocketStompConnection(),
                messageToSendCount,
                intervalBetweenMessagesSec
        );
    }

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public VirtualClient standardVirtualClient() {
        return new VirtualClient(
                webSocketConfig.webSocketStandardConnection(),
                messageToSendCount,
                intervalBetweenMessagesSec
        );
    }
}
