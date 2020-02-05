package org.fireplume.config.security;

public abstract class Role {
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    private Role() {
    }

}
