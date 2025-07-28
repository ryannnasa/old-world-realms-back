package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReportPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BattleReportPhotoRepository {

    private static final QueryRunner queryRunner = new QueryRunner();
    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public static List<BattleReportPhoto> findAll() throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReportPhoto>> resultHandler = new BeanListHandler<>(BattleReportPhoto.class);
            try {
                return queryRunner.query(conn, "SELECT * FROM battlereportphoto", resultHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static BattleReportPhoto findById(int id) throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReportPhoto>> resultHandler = new BeanListHandler<>(BattleReportPhoto.class);
            try {
                List<BattleReportPhoto> photos = queryRunner.query(conn, "SELECT * FROM battlereportphoto WHERE idBattleReportPhoto = ?", resultHandler, id);
                return photos.isEmpty() ? null : photos.get(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static List<BattleReportPhoto> findByBattleReportId(int idBattleReport) throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReportPhoto>> resultHandler = new BeanListHandler<>(BattleReportPhoto.class);
            try {
                return queryRunner.query(conn, "SELECT * FROM battlereportphoto WHERE battleReport_idBattleReport = ?", resultHandler, idBattleReport);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(BattleReportPhoto photo) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return create(photo, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(BattleReportPhoto photo, Connection conn) throws SQLException {
        String sql = "INSERT INTO battlereportphoto (nameBattleReportPhoto, battleReport_idBattleReport) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, photo.getNameBattleReportPhoto());
            stmt.setInt(2, photo.getBattleReport_idBattleReport());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating BattleReportPhoto failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating BattleReportPhoto failed, no ID obtained.");
                }
            }
        }
    }

    public static int update(int id, BattleReportPhoto photo) throws SQLException {
        String sql = "UPDATE battlereportphoto SET nameBattleReportPhoto = ?, battleReport_idBattleReport = ? WHERE idBattleReportPhoto = ?";

        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, sql,
                        photo.getNameBattleReportPhoto(),
                        photo.getBattleReport_idBattleReport(),
                        id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int deleteByBattleReportId(int battleReportId) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return deleteByBattleReportId(battleReportId, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int deleteByBattleReportId(int battleReportId, Connection conn) throws SQLException {
        return queryRunner.update(conn,
                "DELETE FROM battlereportphoto WHERE battleReport_idBattleReport = ?",
                battleReportId);
    }

    public static int delete(int id) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, "DELETE FROM battlereportphoto WHERE idBattleReportPhoto = ?", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static BattleReportPhoto findByFileNameAndBattleReportId(String fileName, int battleReportId) throws SQLException {
        return db.withConnection(conn -> {
            String query = "SELECT * FROM battlereportphoto WHERE nameBattleReportPhoto = ? AND battleReport_idBattleReport = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, fileName);
                stmt.setInt(2, battleReportId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new BattleReportPhoto(
                                rs.getInt("idBattleReportPhoto"),
                                rs.getString("nameBattleReportPhoto"),
                                rs.getInt("battleReport_idBattleReport")
                        );
                    } else {
                        return null;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
