package org.fireplume.config;

import org.fireplume.config.security.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

@SuppressWarnings("Duplicates")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter authFilter;

    // !!! For using Scope session in security !!!
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests().antMatchers("/**").permitAll();

        http.authorizeRequests().antMatchers("/log-in").anonymous();
        http.authorizeRequests().antMatchers("/log-in").not().authenticated();

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilter(authenticationFilter());

        http.exceptionHandling().accessDeniedPage(String.format("%s?error=access", "/log-in"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
    }
}
