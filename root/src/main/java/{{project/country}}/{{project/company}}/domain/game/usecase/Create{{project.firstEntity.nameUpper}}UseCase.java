package ru.peter.domain.{{project.firstEntity.name}}.usecase;

import lombok.RequiredArgsConstructor;
import ru.peter.domain.common.port.IdGenerator;
import ru.peter.domain.common.usecase.UseCase;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;


@RequiredArgsConstructor
public class Create{{project.firstEntity.nameUpper}}UseCase extends UseCase<Create{{project.firstEntity.nameUpper}}UseCase.InputValues, UseCase.SingleOutput<{{project.firstEntity.nameUpper}}Entity>> {

    private final {{project.firstEntity.nameUpper}}Repository repository;
    private final IdGenerator idGenerator;

    @Override
    public UseCase.SingleOutput<{{project.firstEntity.nameUpper}}Entity> execute(InputValues inputValues) {

        validate(inputValues);

        {{project.firstEntity.nameUpper}}Entity {{project.firstEntity.name}} = new {{project.firstEntity.nameUpper}}Entity();

        {{project.firstEntity.name}}.setId(idGenerator.generate());

        {{#fields}}
        {{project.firstEntity.name}}.set{{nameUpper}}(inputValues.{{name}});
        {{/fields}}

        return SingleOutput.of(repository.save({{project.firstEntity.name}}));
    }

    private void validate(InputValues inputValues) {
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class InputValues implements UseCase.InputValues {

        {{#fields}}
        private {{type}} {{name}};
        {{/fields}}
    }
}
