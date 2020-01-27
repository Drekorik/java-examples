package org.fireplume.db.ch1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserJdbcTemplate userJdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        init();
        List<UserEntity> userEntities = Arrays.asList(
                new UserEntity(UUID.randomUUID().toString(), "name1", "email1"),
                new UserEntity(UUID.randomUUID().toString(), "name2", "email2")
        );
        userJdbcTemplate.insert(userEntities);
        System.out.println(userJdbcTemplate.getOneByName("name1"));
    }


    private void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS public.\"user\"");
        jdbcTemplate.execute(
                "CREATE TABLE public.user\n" +
                        "(\n" +
                        "    id VARCHAR(255) PRIMARY KEY NOT NULL,\n" +
                        "    name VARCHAR(255) NOT NULL,\n" +
                        "    email VARCHAR(255)\n" +
                        ");\n" +
                        "CREATE UNIQUE INDEX user_id_uindex ON public.\"user\" (id);");
    }
}
