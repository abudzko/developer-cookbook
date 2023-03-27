package com.budzko.token.client;

import com.budzko.token.storage.http.HttpClientConfig;
import com.budzko.token.storage.redis.RedisConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RedisConfig.class, HttpClientConfig.class})
@ConditionalOnProperty(
        value = "security.token.client",
        havingValue = "true",
        matchIfMissing = false
)
@ComponentScan
@Slf4j
public class TokenStarterClientAutoConfiguration {
    @PostConstruct
    void init() {
        log.info("Init: " + getClass().getSimpleName());
    }
}
