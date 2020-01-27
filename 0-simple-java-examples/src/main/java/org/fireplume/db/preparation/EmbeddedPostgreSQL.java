package org.fireplume.db.preparation;

import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("Duplicates")
public class EmbeddedPostgreSQL {
    public static void main(String[] args) throws IOException {
        final PostgresConfig config = new PostgresConfig(
                Version.V9_6_11,
                new AbstractPostgresConfig.Net("localhost", 38105),
//                new AbstractPostgresConfig.Net("localhost", Network.getFreeServerPort()),
                new AbstractPostgresConfig.Storage("TMP"),
                new AbstractPostgresConfig.Timeout(),
                new AbstractPostgresConfig.Credentials("user", "pass")
        );

        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        PostgresExecutable exec = runtime.prepare(config);
        exec.start();
        System.out.println("***\tjdbc:postgresql://localhost:38105/TMP\t***");
        System.out.println("***\tDriver: org.postgresql.Driver\t***");
        System.out.println("***\tStarted, pres any key to stop\t***");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        exec.stop();
    }
}
