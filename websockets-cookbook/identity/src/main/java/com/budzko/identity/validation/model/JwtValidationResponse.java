package com.budzko.identity.validation.model;

import lombok.Data;

@Data
public class JwtValidationResponse {
    private Boolean isJwtValid = false;
}
