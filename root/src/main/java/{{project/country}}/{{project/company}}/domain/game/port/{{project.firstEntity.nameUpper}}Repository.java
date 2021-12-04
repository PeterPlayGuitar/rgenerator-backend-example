package ru.peter.domain.{{project.firstEntity.name}}.port;

import ru.peter.domain.common.port.CommonRepository;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import ru.peter.domain.{{project.firstEntity.name}}.port.filters.{{project.firstEntity.nameUpper}}Filters;

public interface {{project.firstEntity.nameUpper}}Repository extends CommonRepository<{{project.firstEntity.nameUpper}}Entity, {{project.firstEntity.nameUpper}}Filters> {
}
