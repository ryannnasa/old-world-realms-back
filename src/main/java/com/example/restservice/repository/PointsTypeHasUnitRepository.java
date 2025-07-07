package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.PointsTypeHasUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PointsTypeHasUnitRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<PointsTypeHasUnit> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<PointsTypeHasUnit>> resultHandler = new BeanListHandler<>(PointsTypeHasUnit.class);
            return queryRunner.query(conn,
                    "SELECT * FROM pointstype_has_unit",
                    resultHandler);
        }
    }
}
