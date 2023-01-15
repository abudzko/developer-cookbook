package com.budzko.identity.model;

import lombok.Data;

@Data
public class UserSignUpRequest {
    private String userLogin;
    private String userPassword;
}
