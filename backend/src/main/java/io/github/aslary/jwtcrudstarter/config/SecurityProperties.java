package io.github.aslary.jwtcrudstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("pki")
public record SecurityProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
