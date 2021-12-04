package ru.peter.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.peter.domain.account.config.AccountConfig;
import ru.peter.domain.account.port.AccountRepository;
import ru.peter.domain.account.port.PasswordEncoder;
import ru.peter.domain.common.port.IdGenerator;
import ru.peter.domain.{{project.firstEntity.name}}.config.{{project.firstEntity.nameUpper}}Config;
import ru.peter.domain.{{project.firstEntity.name}}.port.{{project.firstEntity.nameUpper}}Repository;
import ru.peter.security.passwordEncoder.PeterPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public {{project.firstEntity.nameUpper}}Config {{project.firstEntity.name}}Config({{project.firstEntity.nameUpper}}Repository {{project.firstEntity.name}}Repository, IdGenerator idGenerator) {
        return new {{project.firstEntity.nameUpper}}Config(
                {{project.firstEntity.name}}Repository,
                idGenerator
        );
    }

    @Bean
    public AccountConfig accountConfig(AccountRepository accountRepository, IdGenerator idGenerator, PasswordEncoder passwordEncoder) {
        return new AccountConfig(
                accountRepository,
                idGenerator,
                passwordEncoder
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PeterPasswordEncoder(10);
    }

}
