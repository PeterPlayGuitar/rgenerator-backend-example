package ru.peter.domain.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@Getter
public class SearchResult<Entity> {

    private long count;
    private List<Entity> items;
}
