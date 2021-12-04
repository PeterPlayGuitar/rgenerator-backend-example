package ru.peter.api.{{project.firstEntity.name}}.mappers;

import ru.peter.api.{{project.firstEntity.name}}.response.{{project.firstEntity.nameUpper}}Response;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;

public class {{project.firstEntity.nameUpper}}ResponseMapper {


    public static {{project.firstEntity.nameUpper}}Response map({{project.firstEntity.nameUpper}}Entity entity) {

        return {{project.firstEntity.nameUpper}}Response.of(
                entity.getId()
        );
    }
}
