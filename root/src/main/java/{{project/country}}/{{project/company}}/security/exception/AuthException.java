package ru.peter.security.exception;

import ru.peter.domain.common.exception.BadRequestException;

public class AuthException extends BadRequestException {
    public AuthException() {
        super("Authentication failed. Try other username or password");
    }
}
