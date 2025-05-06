package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountRule;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MountRuleRepository {

    public static List<MountRule> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<MountRule>> resultHandler = new BeanListHandler<>(MountRule.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM mountRule",
                resultHandler);
    }
}
