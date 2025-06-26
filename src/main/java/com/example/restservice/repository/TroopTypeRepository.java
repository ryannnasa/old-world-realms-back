package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.TroopType;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TroopTypeRepository {

    public static List<TroopType> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DatabaseSingleton instanceDatabaseSingleton = DatabaseSingleton.getInstance();
        ResultSetHandler<List<TroopType>> resultHandler = new BeanListHandler<>(TroopType.class);
        return queryRunner.query(instanceDatabaseSingleton.getConn(),
                "SELECT * FROM trooptype",
                resultHandler);
    }
}
