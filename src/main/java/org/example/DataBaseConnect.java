package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnect {

    private static Connection Db;

    private DataBaseConnect() {

    }

    public static Connection getDb() {
        if (Db == null) {
            String url = "jdbc:postgresql://localhost:5432/database-dict";
            String username = "postgres";
            String password = "12345678";

            try {
                Class.forName("org.postgresql.Driver");
                Db = DriverManager.getConnection(
                        url, username, password);
                System.out.println("Database connect");
            } catch (Exception ex) {
                System.err.println("Ошибка: " + ex);
                System.exit(1);
            }
        }
        return Db;
    }

}
