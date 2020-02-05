package org.fireplume.config.security.auth_def;

import org.fireplume.config.security.handlers.AuthFailureHandler;
import org.fireplume.config.security.handlers.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
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
            AuthSuccessHandler authSuccessHandler,
            AuthFailureHandler authFailureHandler,
            AuthManager authManager
    ) {
        super(new AntPathRequestMatcher("/log-in", "POST"));
        this.setAuthenticationSuccessHandler(authSuccessHandler);
        this.setAuthenticationFailureHandler(authFailureHandler);
        this.setAuthenticationManager(authManager);
        this.setUsernameParameter(usernameParameter);
        this.setPasswordParameter(passwordParameter);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Cookie jsessionid = Stream.of(request.getCookies())
                .filter(cookie -> cookie.getName().equals("JSESSIONID"))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("No JSESSIONID, strange...") {
                });
        return getAuthenticationManager()
                .authenticate(new AuthToken(obtainUsername(request), obtainPassword(request),
                        Collections.singletonList(jsessionid.getValue()), null));
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
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
