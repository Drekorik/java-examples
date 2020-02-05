package org.fireplume.config.security.auth_anon;

import org.fireplume.config.security.AuthGrantedAuthority;
import org.fireplume.config.security.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.UUID;

@Component
public class AnonymousAuthFilter extends AnonymousAuthenticationFilter {
    public AnonymousAuthFilter() {
        super(UUID.randomUUID().toString());
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest request) {
        return new AnonymousAuthToken(
                UUID.randomUUID().toString(),
                "Anonymous",
                Collections.singleton(new AuthGrantedAuthority(Role.ANONYMOUS))
        );
    }


}
