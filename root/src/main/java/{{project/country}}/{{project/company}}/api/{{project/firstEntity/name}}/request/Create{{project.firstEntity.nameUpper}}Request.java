package ru.peter.api.{{project.firstEntity.name}}.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peter.domain.{{project.firstEntity.name}}.usecase.Create{{project.firstEntity.nameUpper}}UseCase;

@Getter
@Setter
@NoArgsConstructor
public class Create{{project.firstEntity.nameUpper}}Request {


    public Create{{project.firstEntity.nameUpper}}UseCase.InputValues toInputValues(){
        return Create{{project.firstEntity.nameUpper}}UseCase.InputValues.of();
    }
}
