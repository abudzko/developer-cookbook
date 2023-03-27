package com.budzko.token.service;

import org.springframework.stereotype.Service;

@Service
public class JwtValidator {

    public boolean validateJwt(String jwt) {
        return true;
    }

}
