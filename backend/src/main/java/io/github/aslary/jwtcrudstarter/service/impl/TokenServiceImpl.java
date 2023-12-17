package io.github.aslary.jwtcrudstarter.service.impl;

import io.github.aslary.jwtcrudstarter.entity.User;
import io.github.aslary.jwtcrudstarter.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder encoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        var role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var user = (User) authentication.getPrincipal();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(12, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("role", role)
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
