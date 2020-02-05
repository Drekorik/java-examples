package org.fireplume.prototype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    @Qualifier("wrongSingleton")
//    @Qualifier("correctSingleton")
    private Singleton singleton;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
        log.info("Started");
    }

    @Override
    public void run(String... args) {
        new Thread(() -> singleton.printPrototype()).start();
        new Thread(() -> singleton.printPrototype()).start();
        new Thread(() -> singleton.printPrototype()).start();
    }
}
