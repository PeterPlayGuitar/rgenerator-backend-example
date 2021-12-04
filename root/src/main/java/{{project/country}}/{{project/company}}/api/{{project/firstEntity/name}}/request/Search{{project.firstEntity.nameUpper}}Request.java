package ru.peter.api.{{project.firstEntity.name}}.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peter.api.common.request.BaseSearchRequest;
import ru.peter.domain.{{project.firstEntity.name}}.port.filters.{{project.firstEntity.nameUpper}}Filters;

@Getter
@Setter
@NoArgsConstructor
public class Search{{project.firstEntity.nameUpper}}Request extends BaseSearchRequest {

    public {{project.firstEntity.nameUpper}}Filters toFilters() {
        return new {{project.firstEntity.nameUpper}}Filters(limit, offset);
    }
}
