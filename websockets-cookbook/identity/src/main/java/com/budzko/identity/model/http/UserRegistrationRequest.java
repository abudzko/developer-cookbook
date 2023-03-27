package com.budzko.identity.model.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRequest {
    private String userName;
    private String password;
}
