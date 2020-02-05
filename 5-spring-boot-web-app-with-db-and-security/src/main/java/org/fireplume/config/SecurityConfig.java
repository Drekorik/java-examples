package org.fireplume.config;

import org.fireplume.config.security.AuthFailureHandler;
import org.fireplume.config.security.AuthFilter;
import org.fireplume.config.security.AuthProvider;
import org.fireplume.config.security.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextListener;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    // !!! For using Scope session in security !!!
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public AuthFilter authenticationFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/log-in", "POST"));
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authFailureHandler);
//        authFilter.setUsernameParameter("user");
//        authFilter.setPasswordParameter("pass");
        return authFilter;
    }

    /**
     * {@link AuthenticationManager} check {@link org.springframework.security.core.Authentication} for
     *
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authProvider));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests().antMatchers("/**").permitAll();

        http.authorizeRequests().antMatchers("/log-in").anonymous();
        http.authorizeRequests().antMatchers("/log-in").not().authenticated();

        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedPage(String.format("%s?error=access", "/log-in"));
    }
}
