package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.UnitDescription;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UnitDescriptionRepository {

    public static List<UnitDescription> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<UnitDescription>> resultHandler = new BeanListHandler<>(UnitDescription.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM unitdescription",
                resultHandler);
    }
}
