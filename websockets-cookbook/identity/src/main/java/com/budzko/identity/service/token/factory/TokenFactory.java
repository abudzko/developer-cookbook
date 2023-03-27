package com.budzko.identity.service.token.factory;

import com.budzko.identity.utils.DateUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenFactory {
    private static JwtBuilder preconfiguredTokenBuilder(TokenConfig tokenConfig) {
        return Jwts.builder()
                .setSubject(tokenConfig.getLogin())
                .setIssuedAt(DateUtils.toDate(tokenConfig.getNow()))
                .signWith(tokenConfig.getPrivateKey(), tokenConfig.getAlgorithm());
    }

    public String buildAccessToken(TokenConfig tokenConfig) {
        return preconfiguredTokenBuilder(tokenConfig)
                .setExpiration(DateUtils.toDate(tokenConfig.getAccessTokenExpireAt()))
                .compact();
    }

    public String buildRefreshToken(TokenConfig tokenConfig) {
        return preconfiguredTokenBuilder(tokenConfig)
                .setExpiration(DateUtils.toDate(tokenConfig.getRefreshTokenExpireAt()))
                .compact();
    }
}
