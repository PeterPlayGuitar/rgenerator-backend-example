package ru.peter.api.auth.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class AuthRequest {

    private String username;
    private String password;
}
