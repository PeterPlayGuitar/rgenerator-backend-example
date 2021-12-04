package ru.peter.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.peter.api.auth.request.AuthRequest;
import ru.peter.domain.account.entity.AccountEntity;
import ru.peter.security.exception.AuthException;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final String secretKey;
    private final int expirationTimeInSeconds;

    public String attemptAuthentication(AuthRequest authRequest) throws AuthenticationException {

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return checkAuthentication(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String checkAuthentication(Authentication authentication) throws IOException {
        if (authentication.isAuthenticated()) return authenticationSuccess(authentication);
        else
            throw new AuthException();
    }

    private String authenticationSuccess(Authentication authResult) throws IOException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("accountId", ((AccountEntity) authResult.getPrincipal()).getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 1000 * expirationTimeInSeconds))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        return token;
    }
}
