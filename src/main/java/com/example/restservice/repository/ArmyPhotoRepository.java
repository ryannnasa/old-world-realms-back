package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.ArmyPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArmyPhotoRepository {

    public static List<ArmyPhoto> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<ArmyPhoto>> resultHandler = new BeanListHandler<>(ArmyPhoto.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
        "SELECT * FROM armyphoto",
        resultHandler);
    }
}
