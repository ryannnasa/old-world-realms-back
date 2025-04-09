package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TypePoints;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypePointsRepository {

    public static List<TypePoints> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TypePoints>> resultHandler = new BeanListHandler<>(TypePoints.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM typepoints",
                resultHandler);
    }
}
