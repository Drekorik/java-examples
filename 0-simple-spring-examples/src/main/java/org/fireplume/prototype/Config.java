package org.fireplume.prototype;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean
    @Scope("prototype")
    public Prototype prototype() {
        return new Prototype();
    }

    @Bean
    public CorrectSingleton correctSingleton() {
        return new CorrectSingleton() {
            @Override
            public Prototype getPrototype() {
                return prototype();
            }
        };
    }
}
