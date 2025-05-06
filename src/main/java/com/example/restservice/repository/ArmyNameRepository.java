package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.ArmyName;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArmyNameRepository {

    public static List<ArmyName> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<ArmyName>> resultHandler = new BeanListHandler<>(ArmyName.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM armyName",
                resultHandler);
    }
}
