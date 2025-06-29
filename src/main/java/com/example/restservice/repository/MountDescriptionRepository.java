package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.MountDescription;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MountDescriptionRepository {

    public static List<MountDescription> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<MountDescription>> resultHandler = new BeanListHandler<>(MountDescription.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM mountdescription",
                resultHandler);
    }
}
