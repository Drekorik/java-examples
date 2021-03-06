package org.fireplume.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * If authorization has ben failed
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    private static final String UNAUTHORIZED = "{\"exception\": \"%s\"}";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(401);
        if (e instanceof AuthenticationException)
            httpServletResponse.getOutputStream().print(String.format(UNAUTHORIZED, e.getMessage()));
    }
}
