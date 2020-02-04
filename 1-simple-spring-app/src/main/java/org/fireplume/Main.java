package org.fireplume;

import org.fireplume.config.AppConfig;
import org.fireplume.services.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        SomeService bean = applicationContext.getBean("someService", SomeService.class);
        String in = "some string ha-ha-ha";
        System.out.println("In:\t" + in);
        System.out.println("out:\t" + bean.printSomeStringWithoutSpaces(in));
    }
}
