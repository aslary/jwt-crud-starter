package io.github.aslary.jwtcrudstarter.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

}
