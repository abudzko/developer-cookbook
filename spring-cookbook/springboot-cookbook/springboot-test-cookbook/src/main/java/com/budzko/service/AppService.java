package com.budzko.service;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AppService {
    private final int value = new Random().nextInt();

    @SneakyThrows
    @PostConstruct
    public void print() {
        Thread.sleep(1000);
        log.info(value + " " + Thread.currentThread());
    }
}
