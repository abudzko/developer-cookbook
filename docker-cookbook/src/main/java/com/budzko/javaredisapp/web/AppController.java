package com.budzko.javaredisapp.web;

import com.budzko.javaredisapp.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AppController {

    private final RedisService redisService;

    @GetMapping("/increment")
    public String increment() {
        log.info("Log4j - Requested: GET /increment");
        return String.valueOf(redisService.increment());
    }
}
