package ru.peter.api.{{project.firstEntity.name}};

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.peter.api.common.response.OkResponse;
import ru.peter.api.{{project.firstEntity.name}}.mappers.{{project.firstEntity.nameUpper}}ResponseMapper;
import ru.peter.api.{{project.firstEntity.name}}.request.Create{{project.firstEntity.nameUpper}}Request;
import ru.peter.api.{{project.firstEntity.name}}.request.Search{{project.firstEntity.nameUpper}}Request;
import ru.peter.api.{{project.firstEntity.name}}.response.{{project.firstEntity.nameUpper}}Response;
import ru.peter.api.{{project.firstEntity.name}}.routes.{{project.firstEntity.nameUpper}}Routes;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.common.entity.SearchResult;
import ru.peter.domain.common.exception.EntityNotFoundException;
import ru.peter.domain.common.usecase.UseCase;
import ru.peter.domain.{{project.firstEntity.name}}.config.{{project.firstEntity.nameUpper}}Config;
import ru.peter.domain.{{project.firstEntity.name}}.entity.{{project.firstEntity.nameUpper}}Entity;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class {{project.firstEntity.nameUpper}}Controller {

    private final {{project.firstEntity.nameUpper}}Config {{project.firstEntity.name}}Config;

    @GetMapping({{project.firstEntity.nameUpper}}Routes.ROOT)
    private OkResponse<SearchResult<{{project.firstEntity.nameUpper}}Response>> search(@ModelAttribute Search{{project.firstEntity.nameUpper}}Request request) {

        SearchResult<{{project.firstEntity.nameUpper}}Entity> entitySearchResult = {{project.firstEntity.name}}Config.search{{project.firstEntity.nameUpper}}sUseCase().execute(
                UseCase.SingleInput.of(request.toFilters())
        ).getValue();
        SearchResult<{{project.firstEntity.nameUpper}}Response> responseSearchResult = SearchResult.of(
                entitySearchResult.getCount(),
                entitySearchResult.getItems().stream().map({{project.firstEntity.nameUpper}}ResponseMapper::map).collect(Collectors.toList())
        );
        return OkResponse.of(responseSearchResult);
    }

    @GetMapping({{project.firstEntity.nameUpper}}Routes.BY_ID)
    private OkResponse<{{project.firstEntity.nameUpper}}Response> getById(@PathVariable String id) {

        {{project.firstEntity.nameUpper}}Response {{project.firstEntity.name}}Response = {{project.firstEntity.name}}Config.get{{project.firstEntity.nameUpper}}ByIdUseCase().execute(UseCase.SingleInput.of(id)).getValue()
                .map({{project.firstEntity.nameUpper}}ResponseMapper::map)
                .orElseThrow(EntityNotFoundException::new);
        return OkResponse.of({{project.firstEntity.name}}Response);
    }

    @PostMapping({{project.firstEntity.nameUpper}}Routes.ROOT)
    private OkResponse<{{project.firstEntity.nameUpper}}Response> create(@RequestBody Create{{project.firstEntity.nameUpper}}Request request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accountId = ((AccountEntity) authentication.getPrincipal()).getId();

        {{project.firstEntity.nameUpper}}Entity {{project.firstEntity.name}}Entity = {{project.firstEntity.name}}Config.create{{project.firstEntity.nameUpper}}UseCase().execute(request.toInputValues()).getValue();
        {{project.firstEntity.nameUpper}}Response {{project.firstEntity.name}}Response = {{project.firstEntity.nameUpper}}ResponseMapper.map({{project.firstEntity.name}}Entity);
        return OkResponse.of({{project.firstEntity.name}}Response);
    }

    @DeleteMapping({{project.firstEntity.nameUpper}}Routes.BY_ID)
    private OkResponse<String> delete(@PathVariable String id) {
        {{project.firstEntity.name}}Config.delete{{project.firstEntity.nameUpper}}UseCase().execute(UseCase.SingleInput.of(id));
        return OkResponse.of("OK");
    }
}
