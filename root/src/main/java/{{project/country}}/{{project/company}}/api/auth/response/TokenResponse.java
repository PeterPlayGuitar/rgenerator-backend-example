package ru.peter.api.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class TokenResponse {

    private String accessToken;

}
