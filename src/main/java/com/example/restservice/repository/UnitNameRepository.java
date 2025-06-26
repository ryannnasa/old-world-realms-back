package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.UnitName;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UnitNameRepository {

    public static List<UnitName> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<UnitName>> resultHandler = new BeanListHandler<>(UnitName.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM unitname",
                resultHandler);
    }
}
