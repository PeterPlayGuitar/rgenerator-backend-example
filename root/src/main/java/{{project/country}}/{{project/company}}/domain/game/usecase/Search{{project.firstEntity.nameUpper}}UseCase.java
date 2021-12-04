package ru.peter.domain.{{project.firstEntity.name}}.usecase;

import lombok.RequiredArgsConstructor;
import ru.peter.domain.common.entity.SearchResult;
import ru.peter.domain.common.usecase.UseCase;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;
import ru.peter.domain.{{project.firstEntity.name}}.port.filters.{{project.firstEntity.nameUpper}}Filters;

@RequiredArgsConstructor
public class Search{{project.firstEntity.nameUpper}}sUseCase extends UseCase<UseCase.SingleInput<{{project.firstEntity.nameUpper}}Filters>, UseCase.SingleOutput<SearchResult<{{project.firstEntity.nameUpper}}Entity>>> {

    private final {{project.firstEntity.nameUpper}}Repository repository;

    @Override
    public SingleOutput<SearchResult<{{project.firstEntity.nameUpper}}Entity>> execute(SingleInput<{{project.firstEntity.nameUpper}}Filters> inputValues) {
        return SingleOutput.of(repository.find(inputValues.getValue()));
    }

}
