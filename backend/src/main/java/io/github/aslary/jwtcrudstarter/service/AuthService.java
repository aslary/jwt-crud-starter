package io.github.aslary.jwtcrudstarter.service;

import io.github.aslary.jwtcrudstarter.dto.LoginDto;
import io.github.aslary.jwtcrudstarter.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
