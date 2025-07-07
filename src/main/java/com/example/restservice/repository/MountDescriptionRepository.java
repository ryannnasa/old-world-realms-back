package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountDescription;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MountDescriptionRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<MountDescription> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<MountDescription>> resultHandler = new BeanListHandler<>(MountDescription.class);
            return queryRunner.query(conn,
                    "SELECT * FROM mountdescription",
                    resultHandler);
        }
    }
}
