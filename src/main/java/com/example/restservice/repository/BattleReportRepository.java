package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BattleReportRepository {

    public static List<BattleReport> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<BattleReport>> resultHandler = new BeanListHandler<>(BattleReport.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM battleReport",
                resultHandler);
    }
}
