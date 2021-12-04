package ru.peter.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.peter.api.auth.request.AuthRequest;
import ru.peter.api.auth.response.TokenResponse;
import ru.peter.api.auth.routes.AuthRoutes;
import ru.peter.api.common.response.OkResponse;
import ru.peter.security.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(AuthRoutes.ROOT)
    OkResponse<TokenResponse> auth(@RequestBody AuthRequest request) {
        String token = authService.attemptAuthentication(request);
        return OkResponse.of(TokenResponse.of(token));
    }
}
