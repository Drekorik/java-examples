package org.fireplume.postgres;

import org.fireplume.postgres.utils.InputStreamToString;
import org.fireplume.postgres.utils.ResourceLoader;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings({"Duplicates", "squid:S106"})
public class EmbeddedPostgres {
    private static final String HOST_ARG = "-h";
    private static final String HOST_DEFAULT = "localhost";

    private static final String PORT_ARG = "-p";
    private static final String PORT_DEFAULT = "38105";

    private static final String DB_NAME_ARG = "-n";
    private static final String DB_NAME_DEFAULT = "tmp";

    private static final String USER_ARG = "-u";
    private static final String USER_DEFAULT = "user";

    private static final String PASS_ARG = "-p";
    private static final String PASS_DEFAULT = "pass";

    private String help;

    public static void main(String[] args) throws IOException {
        new EmbeddedPostgres().execute(args);
    }

    public EmbeddedPostgres() {
        help = InputStreamToString.read(ResourceLoader.getResource("help.txt"));
    }

    public void execute(String... args) throws IOException {
        if (parseHelp(args))
            return;

        final String host = parseArgs(HOST_ARG, HOST_DEFAULT, args);
        final int port = Integer.parseInt(parseArgs(PORT_ARG, PORT_DEFAULT, args));
        final String dbName = parseArgs(DB_NAME_ARG, DB_NAME_DEFAULT, args);
        final String username = parseArgs(USER_ARG, USER_DEFAULT, args);
        final String password = parseArgs(PASS_ARG, PASS_DEFAULT, args);

        final PostgresConfig config = new PostgresConfig(
                Version.V9_6_11,
                new AbstractPostgresConfig.Net(host, port),
//                new AbstractPostgresConfig.Net("localhost", Network.getFreeServerPort()),
                new AbstractPostgresConfig.Storage(dbName),
                new AbstractPostgresConfig.Timeout(),
                new AbstractPostgresConfig.Credentials(username, password)
        );

        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        PostgresExecutable exec = runtime.prepare(config);
        exec.start();
        System.out.printf("***\tjdbc:postgresql://%s:%d/%s\t***%n", host, port, dbName);
        System.out.printf("***\tusername: %s, password:%s\t***%n", username, password);
        System.out.printf("***\tDriver: org.postgresql.Driver  \t***%n");
        System.out.printf("***\tStarted, press any key to stop \t***%n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        System.out.println("***\tStopping...                    \t***");
        exec.stop();
    }

    private boolean parseHelp(String... args) {
        for (String arg : args) {
            if (arg.equals("--help")) {
                System.out.println(help);
                return true;
            }
        }
        return false;
    }

    private String parseArgs(String arg, String def, String... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(arg) && ++i < args.length) {
                return args[i];
            }
        }
        return def;
    }
}
