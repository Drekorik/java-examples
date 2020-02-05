package org.fireplume.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Extract necessary fields from request and create {@link Authentication}
 */
@Component
public class AuthFilter extends AbstractAuthenticationProcessingFilter {
    private String usernameParameter = "username";
    private String passwordParameter = "password";

    @Autowired
    public AuthFilter(
            AuthManager authManager,
            AuthSuccessHandler authSuccessHandler,
            AuthFailureHandler authFailureHandler
    ) {
        super(new AntPathRequestMatcher("/log-in", "POST"));
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/log-in", "POST"));
        this.setAuthenticationManager(authManager);
        this.setAuthenticationSuccessHandler(authSuccessHandler);
        this.setAuthenticationFailureHandler(authFailureHandler);
        this.setUsernameParameter("username");
        this.setPasswordParameter("password");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final String[] jsessionid = {"No jsession id"};
        if (request.getCookies() != null) {
            Stream.of(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("JSESSIONID"))
                    .findFirst()
                    .ifPresent(cookie -> jsessionid[0] = cookie.getValue());
        }
        return getAuthenticationManager()
                .authenticate(new AuthToken(
                        obtainUsername(request),
                        obtainPassword(request),
                        Collections.singletonList(jsessionid[0]),
                        null));
    }

    private String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    private String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
