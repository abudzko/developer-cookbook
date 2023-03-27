package com.budzko.javaredisapp.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecretService {
    @Value("${TEST-SECRET:DEFAULT}")
    private String testSecret;

    @PostConstruct
    void run() {
        log.info("Value from secrets: {}", testSecret);
    }
}
