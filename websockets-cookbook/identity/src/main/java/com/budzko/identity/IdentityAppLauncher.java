package com.budzko.identity;

import com.budzko.token.client.EnableTokenClient;
import com.budzko.token.server.EnableTokenServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTokenClient
//@EnableTokenServer
public class IdentityAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(IdentityAppLauncher.class, args);
    }
}
