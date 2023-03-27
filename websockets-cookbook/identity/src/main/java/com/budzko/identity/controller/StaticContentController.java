package com.budzko.identity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class StaticContentController {

    /**
     * User registration form page
     *
     * @return path to static resource
     */
    @GetMapping("/register")
    public String register() {
        log.info("Register page");
        return "/static/register.html";
    }
}
