package ru.peter.domain.{{project.firstEntity.name}}.usecase;

import lombok.RequiredArgsConstructor;
import ru.peter.domain.common.usecase.UseCase;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;

@RequiredArgsConstructor
public class Delete{{project.firstEntity.nameUpper}}UseCase extends UseCase<UseCase.SingleInput<String>, UseCase.SingleOutput<Boolean>> {

    private final {{project.firstEntity.nameUpper}}Repository repository;

    @Override
    public UseCase.SingleOutput<Boolean> execute(UseCase.SingleInput<String> inputValues) {
        return SingleOutput.of(repository.delete(inputValues.getValue()));
    }

}
