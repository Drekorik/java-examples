package db;

import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:10.7-alpine");
        postgreSQLContainer.start();
        System.out.println("Database name: " + postgreSQLContainer.getDatabaseName());
        System.out.println("Username: " + postgreSQLContainer.getUsername());
        System.out.println("Password: " + postgreSQLContainer.getPassword());
        System.out.println("JDBC URL: " + postgreSQLContainer.getJdbcUrl());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press any key to stop...");
        reader.readLine();
    }
}
