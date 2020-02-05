package org.fireplume.transaction;

import org.fireplume.transaction.repositories.RecordsRepository;
import org.fireplume.transaction.threads.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "classpath:transaction/application.properties")
public class Application implements CommandLineRunner {

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private Reader reader;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(recordsRepository.findAllRecordsId());

        new Thread(reader).start();
        new Thread(reader).start();
    }
}
