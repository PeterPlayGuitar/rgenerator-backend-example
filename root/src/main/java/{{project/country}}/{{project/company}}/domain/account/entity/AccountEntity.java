package ru.peter.domain.account.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor(staticName = "of")
public class AccountEntity implements UserDetails {

    private String id;

    public AccountEntity(String id) {
        this.id = id;
    }

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

}
