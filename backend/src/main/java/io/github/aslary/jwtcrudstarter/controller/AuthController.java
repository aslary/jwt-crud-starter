package io.github.aslary.jwtcrudstarter.controller;

import io.github.aslary.jwtcrudstarter.dto.LoginDto;
import io.github.aslary.jwtcrudstarter.dto.RegisterDto;
import io.github.aslary.jwtcrudstarter.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterDto registerDto) {
        return authService.register(registerDto);
    }

}
