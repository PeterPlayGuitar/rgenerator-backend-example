package ru.peter.mongo.{{project.firstEntity.name}}.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import ru.peter.mongo.common.MongoIdGenerator;
import ru.peter.mongo.{{project.firstEntity.name}}.documents.{{project.firstEntity.nameUpper}}Doc;

@Component
@RequiredArgsConstructor
public class {{project.firstEntity.nameUpper}}DocMapper {

    private final MongoIdGenerator mongoIdGenerator;

    public {{project.firstEntity.nameUpper}}Doc of({{project.firstEntity.nameUpper}}Entity {{project.firstEntity.name}}Entity) {

        return {{project.firstEntity.nameUpper}}Doc.of(
                mongoIdGenerator.parse({{project.firstEntity.name}}Entity.getId()),
                {{#fields}}
                {{project.firstEntity.name}}Entity.get{{nameUpper}}(),
                {{/fields}}
        );
    }

    public {{project.firstEntity.nameUpper}}Entity of({{project.firstEntity.nameUpper}}Doc {{project.firstEntity.name}}Doc) {

        return {{project.firstEntity.nameUpper}}Entity.of(
                {{project.firstEntity.name}}Doc.getId().toString(),
                {{#fields}}
                {{project.firstEntity.name}}Doc.get{{nameUpper}}(),
                {{/fields}}
        );
    }
}
