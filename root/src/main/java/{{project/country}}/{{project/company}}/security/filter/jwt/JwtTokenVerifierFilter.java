package ru.peter.security.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.peter.api.common.response.ErrorResponse;
import ru.peter.domain.account.entity.AccountEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private final String secretKey;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("x-auth-token");

        if (token == null || token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build();

            Jws<Claims> parsed = (Jws<Claims>) jwtParser.parse(token);

            Claims body = parsed.getBody();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    AccountEntity.builder()
                            .id(body.get("accountId", String.class))
                            .username(body.getSubject())
                            .build(),
                    null,
                    authorities.stream().map((item) -> new SimpleGrantedAuthority(item.get("authority"))).collect(Collectors.toSet())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            response.resetBuffer();
            response.setStatus(401);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.getOutputStream().print(objectMapper.writeValueAsString(ErrorResponse.of(
                    e.getClass().getSimpleName(),
                    e.getMessage(),
                    401
            )));
            response.flushBuffer();
            return;
        }

        filterChain.doFilter(request, response);
    }
}
