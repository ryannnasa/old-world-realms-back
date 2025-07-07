package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountRule;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MountRuleRepository {

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<MountRule> findAll() throws SQLException {
        try (Connection conn = DatabaseSingleton.getInstance().getConn()) {
            ResultSetHandler<List<MountRule>> resultHandler = new BeanListHandler<>(MountRule.class);
            return queryRunner.query(conn,
                    "SELECT * FROM mountrule",
                    resultHandler);
        }
    }
}
