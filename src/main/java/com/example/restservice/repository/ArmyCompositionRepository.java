package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.ArmyComposition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArmyCompositionRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<ArmyComposition> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<ArmyComposition>> resultHandler = new BeanListHandler<>(ArmyComposition.class);
            return queryRunner.query(conn,
                    "SELECT * FROM armycomposition",
                    resultHandler);
        }
    }
}
