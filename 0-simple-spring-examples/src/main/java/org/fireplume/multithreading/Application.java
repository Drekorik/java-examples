package org.fireplume.multithreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:multithreading/application.properties")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private GlobalVariables globalVariables;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
        log.info("Started");
    }

    @Override
    public void run(String... args) {
        new Thread(() -> {
            Thread thread1 = new Thread(() -> globalVariables.run());
            Thread thread2 = new Thread(() -> globalVariables.run());
            ThreadsHelper.pause(2000);
            thread1.start();
            thread2.start();
        }).start();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}