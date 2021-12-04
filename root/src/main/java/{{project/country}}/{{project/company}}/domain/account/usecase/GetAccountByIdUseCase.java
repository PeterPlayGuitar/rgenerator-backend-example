package ru.peter.domain.account.usecase;

import lombok.RequiredArgsConstructor;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.account.port.AccountRepository;
import ru.peter.domain.common.usecase.UseCase;

import java.util.Optional;

@RequiredArgsConstructor
public class GetAccountByIdUseCase extends UseCase<UseCase.SingleInput<String>, UseCase.SingleOutput<Optional<AccountEntity>>> {

    private final AccountRepository repository;

    @Override
    public SingleOutput<Optional<AccountEntity>> execute(SingleInput<String> inputValues) {
        return SingleOutput.of(repository.findById(inputValues.getValue()));
    }
}
