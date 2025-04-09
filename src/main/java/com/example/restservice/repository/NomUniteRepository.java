package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.NomUnite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class NomUniteRepository {

    public static List<NomUnite> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<NomUnite>> resultHandler = new BeanListHandler<>(NomUnite.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM nomunite",
                resultHandler);
    }
}
