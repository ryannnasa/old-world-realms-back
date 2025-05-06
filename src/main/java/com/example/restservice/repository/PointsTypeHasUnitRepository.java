package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.PointsTypeHasUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PointsTypeHasUnitRepository {

    public static List<PointsTypeHasUnit> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<PointsTypeHasUnit>> resultHandler = new BeanListHandler<>(PointsTypeHasUnit.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM pointsTypeHasUnit",
                resultHandler);
    }
}
