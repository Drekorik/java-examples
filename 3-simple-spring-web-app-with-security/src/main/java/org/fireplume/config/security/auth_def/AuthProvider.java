package org.fireplume.config.security.auth_def;

import org.fireplume.config.security.AuthGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Provider casts {@link Authentication} to our custom realization without future cast exception
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (!password.equals("pass")) {
            throw new AuthenticationException("Message") {
            };
        }
        AuthToken authToken;
        authToken = new AuthToken("", "secured", "",
                Collections.singletonList(new AuthGrantedAuthority("ROLE_USER")));
        authToken.setAuthenticated(true);
        return authToken;
    }

    public boolean supports(Class<?> arg0) {
        return arg0.equals(AuthToken.class);
    }

}