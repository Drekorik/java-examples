package org.fireplume.profiling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import static java.lang.System.exit;

@SpringBootApplication
@PropertySource(value = "classpath:profiling/application.properties")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private TestClassImpl testClass;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
        log.info("Started");
    }

    @Override
    public void run(String... args) {
        testClass.testMethod1();
        testClass.testMethod2();
        exit(0);
    }
}
