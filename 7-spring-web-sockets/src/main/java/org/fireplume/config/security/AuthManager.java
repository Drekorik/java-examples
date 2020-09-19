package org.fireplume.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthManager extends ProviderManager {

    @Autowired
    public AuthManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
