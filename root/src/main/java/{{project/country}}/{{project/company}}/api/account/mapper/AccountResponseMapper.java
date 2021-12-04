package ru.peter.api.account.mapper;

import ru.peter.api.account.response.AccountResponse;
import ru.peter.domain.account.entity.AccountEntity;

public class AccountResponseMapper {

    public static AccountResponse of(AccountEntity accountEntity) {
        return AccountResponse.of(
                accountEntity.getId(),
                accountEntity.getUsername()
        );
    }
}
