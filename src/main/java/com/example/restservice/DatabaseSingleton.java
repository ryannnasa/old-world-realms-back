package com.example.restservice;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private Connection conn = null;
    private static DatabaseSingleton instance = null;
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/warhammer";
    private static final String USER = "alex";
    private static final String PASS = "Piano!123";

    private DatabaseSingleton() {
        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            System.out.println("Connexion à la base de données...");
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
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

