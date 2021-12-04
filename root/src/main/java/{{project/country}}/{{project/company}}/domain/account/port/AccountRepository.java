package ru.peter.domain.account.port;

import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.common.port.CommonRepository;
import ru.peter.domain.common.port.filters.CommonSearchFilters;

import java.util.Optional;

public interface AccountRepository extends CommonRepository<AccountEntity, CommonSearchFilters> {

    Optional<AccountEntity> findByUsername(String username);
}
