package com.budzko.cookbook.websockets.client.virtual;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VirtualClientStarter {
    private final VirtualClientFactory virtualClientFactory;

    private final int virtualClientCount = 1;

    @PostConstruct
    void init() {
        for (int i = 0; i < virtualClientCount; i++) {
            virtualClientFactory.createStompVirtualClient().start();
        }
    }
}
