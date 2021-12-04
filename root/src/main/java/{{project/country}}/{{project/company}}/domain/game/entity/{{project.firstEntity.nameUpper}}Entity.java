package ru.peter.domain.{{project.firstEntity.name}}.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class {{project.firstEntity.nameUpper}}Entity {

    private String id;

    {{#fields}}
    private {{type}} {{name}};
    {{/fields}}
}
