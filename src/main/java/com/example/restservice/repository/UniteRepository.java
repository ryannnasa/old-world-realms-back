package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Unite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UniteRepository {

    public static List<Unite> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<Unite>> resultHandler = new BeanListHandler<>(Unite.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM unite",
                resultHandler);
    }
}
