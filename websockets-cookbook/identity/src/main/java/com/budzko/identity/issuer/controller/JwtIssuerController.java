package com.budzko.identity.issuer.controller;

import com.budzko.identity.issuer.model.UserCredentials;
import com.budzko.identity.issuer.service.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtIssuerController {
    private JwtIssuer jwtIssuer;

    @RequestMapping("/issueJwt")
    public String issueJwt(@RequestBody UserCredentials userCredentials) {
        return jwtIssuer.issueJwt(userCredentials);
    }
}
