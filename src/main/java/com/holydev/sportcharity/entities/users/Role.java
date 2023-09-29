package com.holydev.sportcharity.entities.users;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER,
    USER_IN_DEP,
    DEP_HEAD,
    FUND_AGENT,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}