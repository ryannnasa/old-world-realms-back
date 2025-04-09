package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TypeTroupePrincipal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeTroupePrincipalRepository {

    public static List<TypeTroupePrincipal> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TypeTroupePrincipal>> resultHandler = new BeanListHandler<>(TypeTroupePrincipal.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM typetroupeprincipal",
                resultHandler);
    }
}
