package ru.peter.security.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.peter.api.auth.request.AuthRequest;
import ru.peter.api.auth.response.TokenResponse;
import ru.peter.api.common.response.ErrorResponse;
import ru.peter.domain.account.entity.AccountEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class AuthenticationJwtFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final String secretKey;
    private final int expirationTimeInSeconds;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            AuthRequest authRequest = objectMapper.readValue(request.getInputStream(), AuthRequest.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.resetBuffer();
        response.setStatus(400);
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getOutputStream().print(objectMapper.writeValueAsString(ErrorResponse.of(
                failed.getClass().getSimpleName(),
                "Wrong username or password",
                400
        )));
        response.flushBuffer();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("accountId", ((AccountEntity) authResult.getPrincipal()).getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 1000 * expirationTimeInSeconds))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        response.getOutputStream().print(objectMapper.writeValueAsString(TokenResponse.of(
                token
        )));
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
