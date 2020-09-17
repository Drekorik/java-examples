package org.fireplume.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Provider casts {@link Authentication} to our custom realization without future cast exception
 * <p>
 * If we change credentials to something else, we should set token authenticated
 */
@SuppressWarnings("Duplicates")
@Component
public class AuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {
        String password = (String) authentication.getCredentials();

        if (!password.equals("Password")) {
            return new AuthToken(
                    "ANONYMOUS",
                    "ANONYMOUS",
                    authentication.getDetails(),
                    Collections.singletonList(new AuthGrantedAuthority(Role.ANONYMOUS))
            );
        }
        return new AuthToken(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                authentication.getDetails(),
                Collections.singletonList(new AuthGrantedAuthority(Role.ADMIN))
        );
    }

    public boolean supports(Class<?> arg0) {
        return arg0.equals(AuthToken.class);
    }

}