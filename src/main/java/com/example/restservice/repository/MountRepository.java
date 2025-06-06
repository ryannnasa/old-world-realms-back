package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Mount;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MountRepository {

    public static List<Mount> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<Mount>> resultHandler = new BeanListHandler<>(Mount.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM mount",
                resultHandler);
    }
}
