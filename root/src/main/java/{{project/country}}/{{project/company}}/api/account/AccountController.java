package ru.peter.api.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.peter.api.account.mapper.AccountResponseMapper;
import ru.peter.api.account.request.CreateAccountRequest;
import ru.peter.api.account.response.AccountResponse;
import ru.peter.api.account.routes.AccountRoutes;
import ru.peter.api.common.response.OkResponse;
import ru.peter.domain.account.config.AccountConfig;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.domain.account.exception.AccountNotFoundException;
import ru.peter.domain.common.usecase.UseCase;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountConfig config;

    @PostMapping(AccountRoutes.ROOT_NOT_SECURE)
    private OkResponse<AccountResponse> create(@RequestBody CreateAccountRequest request) {
        AccountEntity accountEntity = config.createAccountUseCase().execute(request.toInputValues()).getValue();
        AccountResponse accountResponse = AccountResponseMapper.of(accountEntity);
        return OkResponse.of(accountResponse);
    }

    @GetMapping(AccountRoutes.BY_ID)
    private OkResponse<AccountResponse> getById(@PathVariable String id) {
        AccountEntity accountEntity = config.getAccountByIdUseCase().execute(UseCase.SingleInput.of(id)).getValue()
                .orElseThrow(AccountNotFoundException::new);
        AccountResponse accountResponse = AccountResponseMapper.of(accountEntity);
        return OkResponse.of(accountResponse);
    }

    @GetMapping(AccountRoutes.MY_ACCOUNT)
    private OkResponse<AccountResponse> myAccount() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity accountEntity = ((AccountEntity) authentication.getPrincipal());
        AccountResponse accountResponse = AccountResponseMapper.of(accountEntity);
        return OkResponse.of(accountResponse);
    }
}
