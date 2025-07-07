package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Alliance;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AllianceRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<Alliance> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<Alliance>> resultHandler = new BeanListHandler<>(Alliance.class);
            return queryRunner.query(conn,
                    "SELECT * FROM alliance",
                    resultHandler);
        }
    }
}
