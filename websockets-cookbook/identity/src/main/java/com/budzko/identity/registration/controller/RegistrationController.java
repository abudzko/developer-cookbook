package com.budzko.identity.registration.controller;

import com.budzko.identity.registration.model.UserCredentials;
import com.budzko.identity.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public void registerUser(UserCredentials userCredentials) {
        registrationService.registerUser(userCredentials);
    }
}
