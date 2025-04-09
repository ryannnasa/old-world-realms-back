package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.RoleMonture;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RoleMontureRepository {

    public static List<RoleMonture> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<RoleMonture>> resultHandler = new BeanListHandler<>(RoleMonture.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM rolemonture",
                resultHandler);
    }
}
