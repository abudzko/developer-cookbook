package com.budzko.cookbook.spring.security.session.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController()
@RequestMapping("/")
public class HelloWorldController {

    @RequestMapping(value = "hello-world", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        com.budzko.log.utils.LogUtils.console("Accessing of 'hello-world' endpoint at " + new Date());
        return "Hello World!";
    }
}
