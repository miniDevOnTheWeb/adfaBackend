package com.adfa.adfa.web.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenAuthentication extends AuthenticationException {
    public JwtTokenAuthentication(String message) {
        super(message);
    }
}
