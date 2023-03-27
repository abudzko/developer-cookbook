package com.budzko.token.server;

import com.budzko.token.storage.redis.RedisConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RedisConfig.class)
@ConditionalOnProperty(
        value = "security.token.server",
        havingValue = "true",
        matchIfMissing = false
)
@Slf4j
public class TokenStarterServerAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("Init: " + getClass().getSimpleName());
    }
}
