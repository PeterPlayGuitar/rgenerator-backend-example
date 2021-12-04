package ru.peter.domain.account.exception;

import ru.peter.domain.common.exception.BadRequestException;

public class InvalidPasswordException extends BadRequestException {

    public InvalidPasswordException() {
        super("Password was invalid");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
