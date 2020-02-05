package org.fireplume.config;

import org.fireplume.config.security.auth_anon.AnonymousAuthFilter;
import org.fireplume.config.security.auth_def.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter authFilter;

    @Autowired
    private AnonymousAuthFilter anonymousAuthFilter;

    // !!! For using Scope session in security !!!
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    // change prefix
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests().antMatchers("/static/").permitAll();

        http.authorizeRequests().antMatchers("/log-in").anonymous();
        http.authorizeRequests().antMatchers("/log-in").not().authenticated();

        http.addFilterAt(authFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterAt(anonymousAuthFilter, AnonymousAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedPage(String.format("%s?error=access", "/log-in"));
    }


}
