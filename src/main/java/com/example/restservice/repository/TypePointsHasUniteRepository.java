package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TypePointsHasUnite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypePointsHasUniteRepository {

    public static List<TypePointsHasUnite> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TypePointsHasUnite>> resultHandler = new BeanListHandler<>(TypePointsHasUnite.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM typepointshasunite",
                resultHandler);
    }
}
