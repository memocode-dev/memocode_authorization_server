package dev.memocode.memocode_authorization_server.in.api.security.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
@AllArgsConstructor
public class SecurityAccount implements OAuth2User, Serializable {

    private AccountDTO account;

    private Collection<? extends GrantedAuthority> authorities;

    private String name;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getAuthority().name().toUpperCase()));
        return authorities;
    }

    @Override
    public String getName() {
        return account.getId().toString();
    }
}
