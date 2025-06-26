package com.example.restservice;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private Connection conn = null;
    private static DatabaseSingleton instance = null;
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://uwi0khb6dgldweu4:28gGfGlaWhUuMFQuvZMt@bk5ht5yfi31umkbh3663-mysql.services.clever-cloud.com:3306/bk5ht5yfi31umkbh3663";
    private static final String USER = "uwi0khb6dgldweu4";
    private static final String PASS = "28gGfGlaWhUuMFQuvZMt";

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

