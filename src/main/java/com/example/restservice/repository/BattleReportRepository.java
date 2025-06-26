package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BattleReportRepository {

    private static final QueryRunner queryRunner = new QueryRunner();
    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public static List<BattleReport> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<BattleReport>> resultHandler = new BeanListHandler<>(BattleReport.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM battlereport",
                resultHandler);
    }
    public static BattleReport findById(int id) throws SQLException {
        ResultSetHandler<List<BattleReport>> resultHandler = new BeanListHandler<>(BattleReport.class);
        return queryRunner.query(db.getConn(),
                "SELECT * FROM battlereport WHERE idBattleReport =? ",
                id,resultHandler).getFirst();
    }

    public static int create(BattleReport battleReport) throws SQLException {
        String sql = "INSERT INTO battleReport (nameBattleReport, descriptionBattleReport, battleReportPhoto_idBattleReportPhoto, scenario_idScenario) VALUES (?, ?, ?, ?)";
        return queryRunner.update(db.getConn(), sql,
                battleReport.getNameBattleReport(),
                battleReport.getDescriptionBattleReport(),
                battleReport.getBattleReportPhoto_idBattleReportPhoto(),
                battleReport.getScenario_idScenario()
        );
    }

    public static int update(int id, BattleReport battleReport) throws SQLException {
       String sql = "UPDATE battleReport SET nameBattleReport = ?, descriptionBattleReport = ?, battleReportPhoto_idBattleReportPhoto = ?, scenario_idScenario = ? WHERE idBattleReport = ?";
       return queryRunner.update(db.getConn(), sql,
                battleReport.getNameBattleReport(),
                battleReport.getDescriptionBattleReport(),
                battleReport.getBattleReportPhoto_idBattleReportPhoto(),
                battleReport.getScenario_idScenario(),
                id);
    }

    public static int delete(int id) throws SQLException {
        return queryRunner.update(db.getConn(), "DELETE FROM battleReport WHERE idBattleReport = ?", id);
    }
}
