package com.budzko.token.client;

import com.budzko.token.storage.http.IdentityServiceHttpClient;
import com.budzko.token.storage.redis.RedisClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.PublicKey;
import java.util.Optional;

@Service
public class TokenValidationService {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IdentityServiceHttpClient identityServiceHttpClient;

    private static String extractLogin(String token) {
        String unsignedToken = token.substring(0, token.lastIndexOf(".") + 1);
        Jwt<Header, Claims> headerClaimsJwt = Jwts.parserBuilder().build().parseClaimsJwt(unsignedToken);
        return headerClaimsJwt.getBody().getSubject();
    }

    public void validate(String token) {
        String login = extractLogin(token);
        PublicKey publicKey = getPublicKey(login);
        Jws<Claims> parse = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
    }

    private PublicKey getPublicKey(String login) {
        Optional<PublicKey> publicKeyOptional = getFromCache(login);
        if (publicKeyOptional.isEmpty()) {
            PublicKey publicKey = getFromIdentityService(login);
            redisClient.savePublicKey(login, publicKey);
            return publicKey;
        } else {
            return publicKeyOptional.get();
        }
    }

    private PublicKey getFromIdentityService(String login) {
        try {
            return identityServiceHttpClient.getPublicKey(login);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<PublicKey> getFromCache(String login) {
        return redisClient.getPublicKey(login);
    }
}
