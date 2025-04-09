package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Armee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArmeeRepository {

    public static List<Armee> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<Armee>> resultHandler = new BeanListHandler<>(Armee.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM armee",
                resultHandler);
    }
}
