package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountedUnitPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MountedUnitPhotoRepository {

    public static List<MountedUnitPhoto> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<MountedUnitPhoto>> resultHandler = new BeanListHandler<>(MountedUnitPhoto.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM mountedunitphoto",
                resultHandler);
    }
}
