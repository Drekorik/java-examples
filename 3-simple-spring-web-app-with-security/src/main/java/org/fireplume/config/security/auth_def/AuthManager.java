package org.fireplume.config.security.auth_def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Iterates an {@link Authentication} request through a list of
 * {@link AuthenticationProvider}s.
 */
@Component
public class AuthManager extends ProviderManager {

    @Autowired
    public AuthManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
