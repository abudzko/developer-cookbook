package com.budzko.identity.issuer.service;

import com.budzko.identity.issuer.model.UserCredentials;
import org.springframework.stereotype.Service;

@Service
public class JwtIssuer {
    public String issueJwt(UserCredentials userCredentials) {
        return "JWT";
    }
}
