package io.github.aslary.jwtcrudstarter.controller;

import io.github.aslary.jwtcrudstarter.entity.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * We configured Spring to be secure by default.
 * All endpoints require an authenticated user by default.
 * Exceptions were the /login and /register endpoints,
 * which we declared with requestMatchers().permitAll() inside {@link io.github.aslary.jwtcrudstarter.config.SecurityConfig}.
 */
@RestController
public class DemoController {

    // Being authenticated is enough to call this endpoint
    @GetMapping("anyone")
    public Integer anyone() {
        return 1;
    }

    // Being authenticated AND having the role of "USER" is required to call this endpoint.
    @GetMapping("user")
    @Secured(Role.Fields.USER)
    public Integer user() {
        return 2;
    }

    // Being authenticated AND having the role of "ADMIN" is required to call this endpoint.
    @GetMapping("admin")
    @Secured(Role.Fields.ADMIN)
    public Integer admin() {
        return 3;
    }

}
