package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TypePointsHasUniteMonture;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypePointsHasUniteMontureRepository {

    public static List<TypePointsHasUniteMonture> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TypePointsHasUniteMonture>> resultHandler = new BeanListHandler<>(TypePointsHasUniteMonture.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM typepointshasunitemonture",
                resultHandler);
    }
}
