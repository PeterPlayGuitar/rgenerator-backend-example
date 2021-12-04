package ru.peter.mongo.account.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.mongo.account.document.AccountDoc;
import ru.peter.mongo.common.MongoIdGenerator;

@Component
@RequiredArgsConstructor
public class AccountDocMapper {

    private final MongoIdGenerator idGenerator;

    public AccountEntity of(AccountDoc accountDoc) {
        return AccountEntity.of(
                accountDoc.getId().toString(),
                accountDoc.getAuthorities(),
                accountDoc.getPassword(),
                accountDoc.getUsername(),
                accountDoc.isAccountNonExpired(),
                accountDoc.isAccountNonLocked(),
                accountDoc.isCredentialsNonExpired(),
                accountDoc.isEnabled()
        );
    }

    public AccountDoc of(AccountEntity accountEntity) {
        return AccountDoc.of(
                idGenerator.parse(accountEntity.getId()),
                accountEntity.getAuthorities(),
                accountEntity.getPassword(),
                accountEntity.getUsername(),
                accountEntity.isAccountNonExpired(),
                accountEntity.isAccountNonLocked(),
                accountEntity.isCredentialsNonExpired(),
                accountEntity.isEnabled()
        );
    }
}
