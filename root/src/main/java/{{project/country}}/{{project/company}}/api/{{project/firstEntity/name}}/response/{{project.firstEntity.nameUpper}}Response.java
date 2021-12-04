package ru.peter.api.{{project.firstEntity.name}}.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class {{project.firstEntity.nameUpper}}Response {

    private String id;

}
