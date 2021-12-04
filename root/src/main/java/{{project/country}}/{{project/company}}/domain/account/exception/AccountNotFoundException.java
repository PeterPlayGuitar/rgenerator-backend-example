package ru.peter.domain.account.exception;

import ru.peter.domain.common.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException() {
        super("Account was not found");
    }
}
