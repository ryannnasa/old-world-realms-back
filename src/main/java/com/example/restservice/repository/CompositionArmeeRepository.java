package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.CompositionArmee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CompositionArmeeRepository {

    public static List<CompositionArmee> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<CompositionArmee>> resultHandler = new BeanListHandler<>(CompositionArmee.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM compositionarmee",
                resultHandler);
    }
}
