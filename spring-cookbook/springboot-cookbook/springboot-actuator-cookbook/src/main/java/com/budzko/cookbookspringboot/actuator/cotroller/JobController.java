package com.budzko.cookbookspringboot.actuator.cotroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class JobController {
    @Autowired
    public RestTemplate customRestTemplate;

    @GetMapping("job")
    public void doJob() {
        String uriTemplate = "https://google.com";
        log.info("Request: " + uriTemplate);
        RequestEntity<Void> build = RequestEntity.get(uriTemplate).build();
        ResponseEntity<String> exchange = customRestTemplate.exchange(build, String.class);
        log.info("Response: " + exchange.getBody());
    }
}
