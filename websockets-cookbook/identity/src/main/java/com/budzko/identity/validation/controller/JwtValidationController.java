package com.budzko.identity.validation.controller;

import com.budzko.identity.validation.model.JwtValidationRequest;
import com.budzko.identity.validation.model.JwtValidationResponse;
import com.budzko.identity.validation.service.JwtValidator;
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
