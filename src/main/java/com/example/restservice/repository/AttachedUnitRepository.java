package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.AttachedUnit;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AttachedUnitRepository {

    public static List<AttachedUnit> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<AttachedUnit>> resultHandler = new BeanListHandler<>(AttachedUnit.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM attachedunit",
                resultHandler);
    }
}
