package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReportPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BattleReportPhotoRepository {

    public static List<BattleReportPhoto> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<BattleReportPhoto>> resultHandler = new BeanListHandler<>(BattleReportPhoto.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM battleReportPhoto",
                resultHandler);
    }
}
