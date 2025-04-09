package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TypeTroupe;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeTroupeRepository {

    public static List<TypeTroupe> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TypeTroupe>> resultHandler = new BeanListHandler<>(TypeTroupe.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM typetroupe",
                resultHandler);
    }
}
