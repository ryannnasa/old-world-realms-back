package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Unit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UnitRepository {

    public static List<Unit> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<Unit>> resultHandler = new BeanListHandler<>(Unit.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM unit",
                resultHandler);
    }
}
