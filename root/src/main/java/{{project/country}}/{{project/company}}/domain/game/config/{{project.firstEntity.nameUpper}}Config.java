package ru.peter.domain.{{project.firstEntity.name}}.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.peter.domain.common.port.IdGenerator;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;
import ru.peter.domain.{{project.firstEntity.name}}.usecase.*;

@Accessors(fluent = true)
@Getter
public class {{project.firstEntity.nameUpper}}Config {

    private final Create{{project.firstEntity.nameUpper}}UseCase create{{project.firstEntity.nameUpper}}UseCase;
    private final Delete{{project.firstEntity.nameUpper}}UseCase delete{{project.firstEntity.nameUpper}}UseCase;
    private final Get{{project.firstEntity.nameUpper}}ByIdUseCase get{{project.firstEntity.nameUpper}}ByIdUseCase;
    private final Search{{project.firstEntity.nameUpper}}sUseCase search{{project.firstEntity.nameUpper}}sUseCase;

    public {{project.firstEntity.nameUpper}}Config({{project.firstEntity.nameUpper}}Repository {{project.firstEntity.name}}Repository, IdGenerator idGenerator) {
        this.create{{project.firstEntity.nameUpper}}UseCase = new Create{{project.firstEntity.nameUpper}}UseCase({{project.firstEntity.name}}Repository, idGenerator);
        this.delete{{project.firstEntity.nameUpper}}UseCase = new Delete{{project.firstEntity.nameUpper}}UseCase({{project.firstEntity.name}}Repository);
        this.get{{project.firstEntity.nameUpper}}ByIdUseCase = new Get{{project.firstEntity.nameUpper}}ByIdUseCase({{project.firstEntity.name}}Repository);
        this.search{{project.firstEntity.nameUpper}}sUseCase = new Search{{project.firstEntity.nameUpper}}sUseCase({{project.firstEntity.name}}Repository);
    }
}
