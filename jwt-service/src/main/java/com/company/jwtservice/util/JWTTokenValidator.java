package com.company.jwtservice.util;

import com.company.jwtservice.model.JWTTokenParameter;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public class JWTTokenValidator {
    private final Clock clock = DefaultClock.INSTANCE;
    private final JWTTokenUtil jwtTokenUtil;
    private final JWTTokenParameter jwtTokenParameter;

    public JWTTokenValidator(JWTTokenParameter tokenParameter) {
        this.jwtTokenParameter = tokenParameter;
        this.jwtTokenUtil = new JWTTokenUtil(tokenParameter.getSecret());
    }

    public boolean validaToken(String token, UserDetails userDetails) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return jwtTokenUtil.getExpirationDateFromToken(token).before(clock.now());
    }
}
