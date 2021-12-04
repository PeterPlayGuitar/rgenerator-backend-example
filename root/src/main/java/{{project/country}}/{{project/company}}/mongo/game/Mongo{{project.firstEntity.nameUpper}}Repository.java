package ru.peter.mongo.{{project.firstEntity.name}};

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.peter.domain.common.entity.SearchResult;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;
import ru.peter.domain.{{project.firstEntity.name}}.port.filters.{{project.firstEntity.nameUpper}}Filters;
import ru.peter.mongo.{{project.firstEntity.name}}.documents.{{project.firstEntity.nameUpper}}Doc;
import ru.peter.mongo.{{project.firstEntity.name}}.mapper.{{project.firstEntity.nameUpper}}DocMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class Mongo{{project.firstEntity.nameUpper}}Repository implements {{project.firstEntity.nameUpper}}Repository {

    private final MongoTemplate mongoTemplate;
    private final {{project.firstEntity.nameUpper}}DocMapper mapper;

    @Override
    public SearchResult<{{project.firstEntity.nameUpper}}Entity> find({{project.firstEntity.nameUpper}}Filters {{project.firstEntity.name}}Filters) {

        Criteria criteria = new Criteria().orOperator(
        );

        Query query = new Query(criteria);

        long count = mongoTemplate.count(query, {{project.firstEntity.nameUpper}}Doc.class);

        query.limit({{project.firstEntity.name}}Filters.getLimit());
        query.skip({{project.firstEntity.name}}Filters.getOffset());

        List<{{project.firstEntity.nameUpper}}Doc> {{project.firstEntity.name}}Docs = mongoTemplate.find(query, {{project.firstEntity.nameUpper}}Doc.class);
        List<{{project.firstEntity.nameUpper}}Entity> {{project.firstEntity.name}}Entities = {{project.firstEntity.name}}Docs.stream().map(mapper::of).collect(Collectors.toList());

        return SearchResult.of(count, {{project.firstEntity.name}}Entities);
    }

    @Override
    public {{project.firstEntity.nameUpper}}Entity save({{project.firstEntity.nameUpper}}Entity {{project.firstEntity.name}}Entity) {

        {{project.firstEntity.nameUpper}}Doc doc = mapper.of({{project.firstEntity.name}}Entity);

        return mapper.of(mongoTemplate.save(doc));
    }

    @Override
    public Optional<{{project.firstEntity.nameUpper}}Entity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, {{project.firstEntity.nameUpper}}Doc.class)).map(mapper::of);
    }

    @Override
    public boolean delete(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.remove(query, {{project.firstEntity.nameUpper}}Doc.class).getDeletedCount() > 0;
    }
}
