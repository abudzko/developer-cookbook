package com.budzko.cookbook.kafka.starter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class StarterService {

    @Autowired
    private GenericWebApplicationContext context;


    @PostConstruct
    void init() {
        log.info("");
    }
}
