package ru.peter.domain.common.exception;

public class InvalidInputValuesException extends BadRequestException{
    public InvalidInputValuesException() {
        super("Input values were invalid");
    }
}
