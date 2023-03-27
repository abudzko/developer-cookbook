package com.budzko.javaredisapp.web;

import com.budzko.javaredisapp.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProbeController {

    private final RedisService redisService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/ready")
    public String ready() {
        return String.valueOf(redisService.size());
    }
}
