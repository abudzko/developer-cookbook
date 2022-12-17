package com.budzko.cookbookspringboot.actuator.config;

import org.springframework.web.client.RestTemplate;

public class RestTemplateProvider {
    private final RestTemplate restTemplate;

    public RestTemplateProvider(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }
}
