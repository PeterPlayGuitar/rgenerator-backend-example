package ru.peter.domain.common.port;

import ru.peter.domain.common.entity.SearchResult;
import ru.peter.domain.common.port.filters.CommonSearchFilters;

import java.util.Optional;

public interface CommonRepository<Entity, Filters extends CommonSearchFilters> {

    SearchResult<Entity> find(Filters filters);

    Entity save(Entity entity);

    Optional<Entity> findById(String id);

    boolean delete(String id);
}
