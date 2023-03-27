package com.budzko.token.model;

import lombok.Data;

@Data
public class JwtValidationResponse {
    private Boolean isJwtValid = false;
}
