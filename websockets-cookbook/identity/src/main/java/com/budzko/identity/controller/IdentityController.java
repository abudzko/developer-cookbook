package com.budzko.identity.controller;

import com.budzko.identity.model.UserSignUpRequest;
import com.budzko.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IdentityController {

    private final UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        userService.registerUser(userSignUpRequest);
    }

    @PostMapping("/signIn")
    public void signIn() {
        log.info("signIn");
    }

    @PostMapping("/signOut")
    public void signOut() {
        log.info("signOut");
    }
}
