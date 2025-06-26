package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.PrincipalUnitName;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PrincipalUnitNameRepository {

    public static List<PrincipalUnitName> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<PrincipalUnitName>> resultHandler = new BeanListHandler<>(PrincipalUnitName.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM principalunitname",
                resultHandler);
    }
}
