package com.budzko.cookbook.websockets.client;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebSocketClientLauncher {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebSocketClientLauncher.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
