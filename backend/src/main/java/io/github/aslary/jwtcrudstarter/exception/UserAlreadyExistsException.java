package io.github.aslary.jwtcrudstarter.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

}
