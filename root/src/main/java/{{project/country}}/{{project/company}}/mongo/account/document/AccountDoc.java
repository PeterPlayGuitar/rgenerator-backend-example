package ru.peter.mongo.account.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Document
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
@Setter
public class AccountDoc {

    @Id
    private ObjectId id;

    protected Collection<? extends GrantedAuthority> authorities;
    protected String password;
    @Indexed
    protected String username;
    protected boolean accountNonExpired = true;
    protected boolean accountNonLocked = true;
    protected boolean credentialsNonExpired = true;
    protected boolean enabled = true;
}
