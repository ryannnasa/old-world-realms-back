package com.example.restservice;

import io.github.cdimascio.dotenv.Dotenv;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private final HikariDataSource dataSource;
    private final Dotenv dotenv;

    private DatabaseSingleton() {
        dotenv = Dotenv.load();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dotenv.get("DB_URL"));
        config.setUsername(dotenv.get("DB_USER"));
        config.setPassword(dotenv.get("DB_PASS"));
        config.setMaximumPoolSize(5); // Limite le nombre de connexions dans le pool
        dataSource = new HikariDataSource(config);
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }

    public <T> T withConnection(Function<Connection, T> function) throws SQLException {
        try (Connection conn = getConn()) {
            return function.apply(conn);
        }
    }
}
