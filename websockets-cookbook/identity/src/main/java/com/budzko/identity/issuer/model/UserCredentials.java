package com.budzko.identity.issuer.model;

import lombok.Data;

@Data
public class UserCredentials {
    private String login;
    private String password;
}
