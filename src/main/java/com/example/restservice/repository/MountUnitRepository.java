package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MountUnitRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<MountUnit> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<MountUnit>> resultHandler = new BeanListHandler<>(MountUnit.class);
            return queryRunner.query(conn,
                    "SELECT * FROM mountunit",
                    resultHandler);
        }
    }
}
