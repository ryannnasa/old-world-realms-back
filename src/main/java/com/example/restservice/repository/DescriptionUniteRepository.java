package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.DescriptionUnite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DescriptionUniteRepository {

    public static List<DescriptionUnite> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<DescriptionUnite>> resultHandler = new BeanListHandler<>(DescriptionUnite.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM descriptionunite",
                resultHandler);
    }
}
