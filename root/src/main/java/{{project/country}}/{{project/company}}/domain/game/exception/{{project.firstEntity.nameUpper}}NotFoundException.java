package ru.peter.domain.{{project.firstEntity.name}}.exception;

import ru.peter.domain.common.exception.EntityNotFoundException;

public class {{project.firstEntity.nameUpper}}NotFoundException extends EntityNotFoundException {
    public {{project.firstEntity.nameUpper}}NotFoundException() {
        super("{{project.firstEntity.nameUpper}} was not found");
    }
}
