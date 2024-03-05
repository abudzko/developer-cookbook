package com.budzko.cookbook.apache.camel.controller;

import com.budzko.cookbook.apache.camel.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class UserController {

    private final ProducerTemplate producerTemplate;
    private final CamelContext camelContext;
    @GetMapping("/user")
    public User findUser() {
        sendToCamel();
        User user = new User();
        user.setName("C-" + UUID.randomUUID());
        return user;
    }

    private void sendToCamel() {
        var exchangeRequest = ExchangeBuilder.anExchange(camelContext).withBody(getClass().getSimpleName()).build();
        producerTemplate.send("direct:user",exchangeRequest);
    }
}
