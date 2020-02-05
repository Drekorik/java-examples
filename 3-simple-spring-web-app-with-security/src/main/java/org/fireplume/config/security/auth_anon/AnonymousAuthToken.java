package org.fireplume.config.security.auth_anon;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AnonymousAuthToken extends AnonymousAuthenticationToken {

    /**
     * Constructor.
     *
     * @param key         to identify if this object made by an authorised client
     * @param principal   the principal (typically a <code>UserDetails</code>)
     * @param authorities the authorities granted to the principal
     * @throws IllegalArgumentException if a <code>null</code> was passed
     */
    public AnonymousAuthToken(String key, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(key, principal, authorities);
    }
}
