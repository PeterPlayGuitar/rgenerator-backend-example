package ru.peter.domain.{{project.firstEntity.name}}.usecase;

import lombok.RequiredArgsConstructor;
import ru.peter.domain.common.usecase.UseCase;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;

import java.util.Optional;

@RequiredArgsConstructor
public class Get{{project.firstEntity.nameUpper}}ByIdUseCase extends UseCase<UseCase.SingleInput<String>, UseCase.SingleOutput<Optional<{{project.firstEntity.nameUpper}}Entity>>> {

    private final {{project.firstEntity.nameUpper}}Repository repository;

    @Override
    public SingleOutput<Optional<{{project.firstEntity.nameUpper}}Entity>> execute(SingleInput<String> inputValues) {
        return SingleOutput.of(repository.findById(inputValues.getValue()));
    }
}
