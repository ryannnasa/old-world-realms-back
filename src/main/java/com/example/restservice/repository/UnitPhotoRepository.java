package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.UnitPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UnitPhotoRepository {

    public static List<UnitPhoto> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<UnitPhoto>> resultHandler = new BeanListHandler<>(UnitPhoto.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM unitphoto",
                resultHandler);
    }
}
