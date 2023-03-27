package com.budzko.small.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/ping")
    @SneakyThrows
    public String ping() {
        log.info("Ping request");
        return InetAddress.getLocalHost().toString();
    }
}
