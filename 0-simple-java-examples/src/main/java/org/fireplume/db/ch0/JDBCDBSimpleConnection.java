package org.fireplume.db.ch0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCDBSimpleConnection {
    private Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JDBCDBSimpleConnection jdbcdbSimpleConnection = new JDBCDBSimpleConnection();
        System.out.println(jdbcdbSimpleConnection.addUserAndGetAll("Nikita", "SomePass"));
    }

    public JDBCDBSimpleConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:38105/TMP",
                "user",
                "pass"
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS public.Users;" +
                    "CREATE TABLE public.Users\n" +
                    "(\n" +
                    "    id SERIAL PRIMARY KEY NOT NULL,\n" +
                    "    name VARCHAR(255),\n" +
                    "    password VARCHAR(255)\n" +
                    ");"
            );
            statement.execute("INSERT INTO public.Users (name, password)\n" +
                    "VALUES ('user1', 'password1'), ('user1', 'password1');");
        }
    }

    public List<Map<String, String>> getUsersAndPasswords() throws SQLException {
        String sql = "SELECT * FROM public.users";
        List<Map<String, String>> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Map<String, String> r = new HashMap<>();
                r.put(resultSet.getString("name"), resultSet.getString("password"));
                result.add(r);
            }
        }
        return result;
    }

    public void addUser(String user, String password) throws SQLException {
        String sql = "INSERT INTO public.users (name, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }
    }

    public List<Map<String, String>> addUserAndGetAll(String user, String password) {
        try {
            connection.setAutoCommit(false);
            addUser(user, password);
            List<Map<String, String>> usersAndPasswords = getUsersAndPasswords();
//            throw new SQLException();
            connection.commit();
            return usersAndPasswords;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
