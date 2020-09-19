package org.fireplume.controler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request);
//        AuthToken authentication = new AuthToken(
//                "Anonymous",
//                "NONE",
//                null,
//                Collections.singletonList(new AuthGrantedAuthority(Role.ANONYMOUS))
//        );
//        authentication.setAuthenticated(true);
//        SecurityContextHolder.getContext().setAuthentication(
//                authentication
//        );
        return super.preHandle(request, response, handler);
    }
}
