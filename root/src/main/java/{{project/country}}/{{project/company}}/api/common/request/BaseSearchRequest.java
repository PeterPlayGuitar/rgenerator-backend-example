package ru.peter.api.common.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BaseSearchRequest {

    protected Integer limit = 20;
    protected Long offset = 0L;

}
