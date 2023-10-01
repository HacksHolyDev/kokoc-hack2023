package com.holydev.sportcharity.entities.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {

    USER("USER"),
    DEP_HEAD("DEP_HEAD"),
    FUND_AGENT("FUND_AGENT"),
    ADMIN("ADMIN");

    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }
}