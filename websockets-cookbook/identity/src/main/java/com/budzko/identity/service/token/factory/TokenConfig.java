package com.budzko.identity.service.token.factory;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Data;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Data
@Builder
public class TokenConfig {
    private String login;
    private LocalDateTime accessTokenExpireAt;
    private LocalDateTime refreshTokenExpireAt;
    private LocalDateTime now;
    private SignatureAlgorithm algorithm;
    private PrivateKey privateKey;
}
