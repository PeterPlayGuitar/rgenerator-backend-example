package ru.peter.security.exception;

import ru.peter.domain.common.exception.BadRequestException;

public class AuthTokenException extends BadRequestException {
    public AuthTokenException(String message) {
        super(message);
    }
}
