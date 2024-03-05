package com.budzko.cookbook.websockets.client.virtual;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VirtualClientStarter {
    private final VirtualClientFactory virtualClientFactory;

    @Value("${websocket.client.virtual.count}")
    private int virtualClientCount;

    @PostConstruct
    void init() {
        for (int i = 0; i < virtualClientCount; i++) {
            virtualClientFactory.standardVirtualClient().start();
        }
    }
}
