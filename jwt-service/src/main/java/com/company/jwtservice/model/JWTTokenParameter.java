package com.company.jwtservice.model;

import lombok.Data;

@Data
public class JWTTokenParameter {
    private String secret;
    private Long expiration;

    public JWTTokenParameter(String secret) {
        this.secret = secret;
    }

    public JWTTokenParameter(String secret, Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }
}
