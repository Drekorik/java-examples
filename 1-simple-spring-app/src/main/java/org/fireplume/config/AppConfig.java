package org.fireplume.config;

import org.fireplume.services.SomeService;
import org.fireplume.services.SomeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SomeService someService() {
        return new SomeServiceImpl();
    }
}
