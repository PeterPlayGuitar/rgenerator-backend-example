package ru.peter.security.passwordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.peter.domain.account.port.PasswordEncoder;

public class PeterPasswordEncoder extends BCryptPasswordEncoder implements PasswordEncoder {
    public PeterPasswordEncoder(int strength) {
        super(strength);
    }
}
