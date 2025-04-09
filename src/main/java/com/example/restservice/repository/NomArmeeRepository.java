package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.NomArmee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class NomArmeeRepository {

    public static List<NomArmee> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<NomArmee>> resultHandler = new BeanListHandler<>(NomArmee.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM nomarmee",
                resultHandler);
    }
}
