package org.fireplume.db.ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        init();
        UserEntity userEntity1 = new UserEntity(null, "name1", "mail1");
        UserEntity userEntity2 = new UserEntity(null, "name2", "mail2");
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);
        System.out.println(userRepository.findAll());
    }

    private void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS public.\"user\"");
        jdbcTemplate.execute(
                "CREATE TABLE public.user\n" +
                        "(\n" +
                        "    id UUID PRIMARY KEY NOT NULL,\n" +
                        "    name VARCHAR(255) NOT NULL,\n" +
                        "    email VARCHAR(255)\n" +
                        ");\n" +
                        "CREATE UNIQUE INDEX user_id_uindex ON public.\"user\" (id);");
    }
}
