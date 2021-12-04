package ru.peter.api.account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AccountResponse {

    private String id;
    private String username;
}
