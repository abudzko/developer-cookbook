package com.budzko.identity.controller;

import com.budzko.identity.model.http.TokenResponse;
import com.budzko.identity.model.http.UserRegistrationRequest;
import com.budzko.identity.service.RegistrationService;
import com.budzko.identity.service.token.TokenIssuer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IdentityController {

    private final RegistrationService registrationService;
    private final TokenIssuer tokenIssuer;

    /**
     * @return Access and Refresh tokens
     */
    @PostMapping("/accessToken")
    public TokenResponse accessToken(Principal principal) {
        log.info("accessToken");
        return tokenIssuer.issueToken(principal.getName());
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        registrationService.registerUser(userRegistrationRequest);
    }

    @GetMapping(value = "/getPublicKey")
    public String getPublicKey(@RequestParam(name = "login") String login) {
        return new String(registrationService.getPublicKey(login), StandardCharsets.UTF_8);
    }
}
