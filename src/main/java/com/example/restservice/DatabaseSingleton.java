package com.example.restservice;

import org.apache.commons.dbutils.DbUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseSingleton {
    private Connection conn = null;
    private static DatabaseSingleton instance = null;
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DatabaseSingleton() {
        Dotenv dotenv = Dotenv.load();
        String dbUrl = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");

        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            System.out.println("Connexion à la base de données...");
            this.conn = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            System.err.println("Erreur JDBC : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
}

