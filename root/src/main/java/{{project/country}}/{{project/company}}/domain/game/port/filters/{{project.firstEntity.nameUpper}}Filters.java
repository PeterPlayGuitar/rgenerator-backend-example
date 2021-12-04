package ru.peter.domain.{{project.firstEntity.name}}.port.filters;

import lombok.Getter;
import ru.peter.domain.common.port.filters.CommonSearchFilters;

@Getter
public class {{project.firstEntity.nameUpper}}Filters extends CommonSearchFilters {

    public {{project.firstEntity.nameUpper}}Filters(Integer limit, Long offset) {
        super(limit, offset);
    }
}
