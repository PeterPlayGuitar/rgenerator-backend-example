package ru.peter.api.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peter.domain.account.usecase.CreateAccountUseCase;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountRequest {

    private String password;
    private String username;

    public CreateAccountUseCase.InputValues toInputValues(){
        return CreateAccountUseCase.InputValues.of(
                password,
                username,
                null
        );
    }
}
