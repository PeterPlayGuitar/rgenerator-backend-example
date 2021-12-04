package ru.peter.domain.common.port.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonSearchFilters {

    private Integer limit;
    private Long offset;
}
