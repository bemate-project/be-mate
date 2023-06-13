package com.bemate.domain.user;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public class UserAuthentication extends AbstractAuthenticationToken {

    private Principal principal;

    public UserAuthentication(Long userNo, Role role) {
        super(Arrays.asList(new SimpleGrantedAuthority(role.name())));
        this.principal = Principal.builder()
                .userNo(userNo)
                .build();
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
