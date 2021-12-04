package ru.peter.domain.account.exception;

import ru.peter.domain.common.exception.BadRequestException;

public class UsernameExistsException extends BadRequestException {
    public UsernameExistsException() {
        super("This username already exists");
    }
}
