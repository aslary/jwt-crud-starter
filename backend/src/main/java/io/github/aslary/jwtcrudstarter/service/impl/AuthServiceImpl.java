package io.github.aslary.jwtcrudstarter.service.impl;

import io.github.aslary.jwtcrudstarter.dto.LoginDto;
import io.github.aslary.jwtcrudstarter.dto.RegisterDto;
import io.github.aslary.jwtcrudstarter.entity.Role;
import io.github.aslary.jwtcrudstarter.entity.User;
import io.github.aslary.jwtcrudstarter.exception.UserAlreadyExistsException;
import io.github.aslary.jwtcrudstarter.mapper.UserMapper;
import io.github.aslary.jwtcrudstarter.repository.UserRepository;
import io.github.aslary.jwtcrudstarter.service.AuthService;
import io.github.aslary.jwtcrudstarter.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = createAuthentication(loginDto.getUsername(), loginDto.getPassword());
        return tokenService.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = userMapper.registerDtoToUser(registerDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        Authentication authentication = createAuthentication(registerDto.getUsername(), registerDto.getPassword());
        return tokenService.generateToken(authentication);
    }

    private Authentication createAuthentication(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
    }
}
