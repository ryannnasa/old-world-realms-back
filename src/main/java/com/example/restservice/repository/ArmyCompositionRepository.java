package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.ArmyComposition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArmyCompositionRepository {

    public static List<ArmyComposition> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<ArmyComposition>> resultHandler = new BeanListHandler<>(ArmyComposition.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM armycomposition",
                resultHandler);
    }
}
