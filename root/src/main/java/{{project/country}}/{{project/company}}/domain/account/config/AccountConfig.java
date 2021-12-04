package ru.peter.domain.account.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.peter.domain.account.port.AccountRepository;
import ru.peter.domain.account.port.PasswordEncoder;
import ru.peter.domain.account.usecase.CreateAccountUseCase;
import ru.peter.domain.account.usecase.GetAccountByIdUseCase;
import ru.peter.domain.common.port.IdGenerator;

@Accessors(fluent = true)
@Getter
public class AccountConfig {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public AccountConfig(AccountRepository accountRepository, IdGenerator idGenerator, PasswordEncoder passwordEncoder) {
        this.createAccountUseCase = new CreateAccountUseCase(accountRepository, idGenerator, passwordEncoder);
        this.getAccountByIdUseCase = new GetAccountByIdUseCase(accountRepository);
    }
}
