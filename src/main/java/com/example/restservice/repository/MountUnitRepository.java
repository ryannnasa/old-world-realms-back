package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MountUnitRepository {

    public static List<MountUnit> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<MountUnit>> resultHandler = new BeanListHandler<>(MountUnit.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM mountunit",
                resultHandler);
    }
}
