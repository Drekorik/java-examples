package org.fireplume.config.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * We use this class as authority unit
 */
public class AuthGrantedAuthority implements GrantedAuthority {

    private String role;

    public AuthGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}