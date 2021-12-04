package ru.peter.mongo.{{project.firstEntity.name}}.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
@Setter
public class {{project.firstEntity.nameUpper}}Doc {

    @Id
    private ObjectId id;

    {{#fields}}
    private {{type}} {{name}};
    {{/fields}}
}
