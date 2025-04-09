package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.UniteRattachee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UniteRattacheeRepository {

    public static List<UniteRattachee> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<UniteRattachee>> resultHandler = new BeanListHandler<>(UniteRattachee.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM uniterattachee",
                resultHandler);
    }
}
