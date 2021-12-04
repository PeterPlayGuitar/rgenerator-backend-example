package ru.peter.domain.common.exception;

public class EntityNotFoundException extends NotFoundException{
    public EntityNotFoundException() {
        super("Requested entity was not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
