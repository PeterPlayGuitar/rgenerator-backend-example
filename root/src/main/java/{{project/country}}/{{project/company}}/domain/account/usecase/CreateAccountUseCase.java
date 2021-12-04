package ru.peter.domain.account.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.account.exception.InvalidPasswordException;
import ru.peter.domain.account.exception.UsernameExistsException;
import ru.peter.domain.account.port.AccountRepository;
import ru.peter.domain.account.port.PasswordEncoder;
import ru.peter.domain.common.exception.InvalidInputValuesException;
import ru.peter.domain.common.port.IdGenerator;
import ru.peter.domain.common.usecase.UseCase;

import java.util.Collection;

@RequiredArgsConstructor
public class CreateAccountUseCase extends UseCase<CreateAccountUseCase.InputValues, UseCase.SingleOutput<AccountEntity>> {

    private final AccountRepository repository;
    private final IdGenerator idGenerator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SingleOutput<AccountEntity> execute(InputValues inputValues) {

        validate(inputValues);

        repository.findByUsername(inputValues.username).ifPresent((a) -> {
            throw new UsernameExistsException();
        });

        // validation passed

        AccountEntity accountEntity = new AccountEntity(idGenerator.generate());

        accountEntity.setUsername(inputValues.username);
        accountEntity.setPassword(passwordEncoder.encode(inputValues.password));
        accountEntity.setAuthorities(inputValues.authorities);

        AccountEntity savedEntity = repository.save(accountEntity);

        return SingleOutput.of(savedEntity);
    }

    private void validate(InputValues inputValues) {
        if (inputValues.password == null || inputValues.password.isBlank())
            throw new InvalidInputValuesException();
        if (inputValues.password.length() <= 5)
            throw new InvalidPasswordException("Password was too short");

        if (inputValues.username == null || inputValues.username.isBlank())
            throw new InvalidInputValuesException();
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        private final String password;
        private final String username;
        private final Collection<? extends GrantedAuthority> authorities;
    }
}
