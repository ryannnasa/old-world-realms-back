package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.PointsTypeHasMountUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PointsTypeHasMountUnitRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<PointsTypeHasMountUnit> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<PointsTypeHasMountUnit>> resultHandler = new BeanListHandler<>(PointsTypeHasMountUnit.class);
            return queryRunner.query(conn,
                    "SELECT * FROM pointstype_has_mountunit",
                    resultHandler);
        }
    }
}
