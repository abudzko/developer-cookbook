package com.budzko.token.controller;

import com.budzko.token.model.JwtValidationRequest;
import com.budzko.token.model.JwtValidationResponse;
import com.budzko.token.service.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@RequiredArgsConstructor
public class JwtValidationController {
    private final JwtValidator jwtValidator;

    @PostMapping("/validateJwt")
    public JwtValidationResponse validate(JwtValidationRequest jwtValidationRequest) {
        JwtValidationResponse jwtValidationResponse = new JwtValidationResponse();
        jwtValidationResponse.setIsJwtValid(false);
        if (jwtValidator.validateJwt(jwtValidationRequest.getJwt())) {
            jwtValidationResponse.setIsJwtValid(true);
        }
        return jwtValidationResponse;
    }
}
