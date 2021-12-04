package ru.peter.mongo.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.account.port.AccountRepository;
import ru.peter.domain.common.entity.SearchResult;
import ru.peter.domain.common.port.filters.CommonSearchFilters;
import ru.peter.mongo.account.document.AccountDoc;
import ru.peter.mongo.account.mapper.AccountDocMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoAccountRepository implements UserDetailsService, AccountRepository {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User was not found by username " + s);
                });
    }

    private final MongoTemplate mongoTemplate;
    private final AccountDocMapper mapper;

    @Override
    public SearchResult<AccountEntity> find(CommonSearchFilters filters) {

        Query query = new Query();

        long count = mongoTemplate.count(query, AccountDoc.class);

        query.limit(filters.getLimit());
        query.skip(filters.getOffset());

        List<AccountDoc> accountDocs = mongoTemplate.find(query, AccountDoc.class);
        List<AccountEntity> accountEntities = accountDocs.stream().map(mapper::of).collect(Collectors.toList());

        return SearchResult.of(count, accountEntities);
    }

    @Override
    public AccountEntity save(AccountEntity accountEntity) {

        AccountDoc doc = mapper.of(accountEntity);

        return mapper.of(mongoTemplate.save(doc));
    }

    @Override
    public Optional<AccountEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, AccountDoc.class)).map(mapper::of);
    }

    @Override
    public boolean delete(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.remove(query, AccountDoc.class).getDeletedCount() > 0;
    }

    @Override
    public Optional<AccountEntity> findByUsername(String username) {
        Criteria criteria = Criteria.where("username").is(username);
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, AccountDoc.class)).map(mapper::of);
    }
}

