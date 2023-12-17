package io.github.aslary.jwtcrudstarter.datagenerator;

import io.github.aslary.jwtcrudstarter.entity.Role;
import io.github.aslary.jwtcrudstarter.entity.User;
import io.github.aslary.jwtcrudstarter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("datagen")
public class UserDataGenerator implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {
        userRepository.save(
                User.builder()
                        .role(Role.ADMIN)
                        .firstName("Admin")
                        .lastName("Nimda")
                        .username("admin")
                        .password(passwordEncoder.encode("adminadminadmin"))
                        .build()
        );

        userRepository.save(
                User.builder()
                        .role(Role.USER)
                        .firstName("User")
                        .lastName("Resu")
                        .username("user")
                        .password(passwordEncoder.encode("useruseruser"))
                        .build()
        );
    }
}