package ru.peter.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import ru.peter.mongo.account.MongoAccountRepository;
import ru.peter.security.filter.jwt.JwtTokenVerifierFilter;
import ru.peter.security.service.AuthService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MongoAccountRepository mongoAccountRepository;
    private final ObjectMapper objectMapper;

    @Value("${chess.security.auth.secret}")
    private String keySecret;
    @Value("${chess.security.auth.expiration_time}")
    private Integer expirationTimeInSeconds;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())

                .and()
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //.addFilter(new AuthenticationJwtFilter(authenticationManager(), objectMapper, keySecret, expirationTimeInSeconds))
                .addFilterAfter(new JwtTokenVerifierFilter(keySecret, objectMapper), UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/not-secure/**").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public AuthService authService() throws Exception {
        return new AuthService(authenticationManager(), keySecret, expirationTimeInSeconds);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return mongoAccountRepository;
    }
}