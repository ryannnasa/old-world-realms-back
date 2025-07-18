package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import com.example.restservice.model.Player;
import com.example.restservice.model.BattleReportPhoto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BattleReportRepository {

    private static final QueryRunner queryRunner = new QueryRunner();
    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public static List<BattleReport> findAll() throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReport>> handler = new BeanListHandler<>(BattleReport.class);
            try {
                List<BattleReport> reports = queryRunner.query(conn, "SELECT * FROM battlereport", handler);

                for (BattleReport report : reports) {
                    // Charger joueurs
                    List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setPlayers(players);

                    // Charger photos associées
                    List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setBattleReportPhotos(photos);
                }

                return reports;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static BattleReport findById(int id) throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReport>> handler = new BeanListHandler<>(BattleReport.class);
            try {
                List<BattleReport> reports = queryRunner.query(conn,
                        "SELECT * FROM battlereport WHERE idBattleReport = ?",
                        handler,
                        id);

                if (reports == null || reports.isEmpty()) {
                    return null;
                }

                BattleReport report = reports.get(0);

                // Charger joueurs
                List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                report.setPlayers(players);

                // Charger photos
                List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                report.setBattleReportPhotos(photos);

                return report;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static List<BattleReport> findByUserId(String idUser) throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReport>> handler = new BeanListHandler<>(BattleReport.class);
            try {
                List<BattleReport> reports = queryRunner.query(conn, "SELECT * FROM battlereport WHERE idUser = ?", handler, idUser);

                if (reports == null) {
                    reports = new ArrayList<>();
                }

                for (BattleReport report : reports) {
                    List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setPlayers(players);

                    List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setBattleReportPhotos(photos);
                }

                return reports;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(BattleReport battleReport) throws SQLException {
        String sql = "INSERT INTO battlereport (nameBattleReport, descriptionBattleReport, scenario_idScenario, armyPoints, idUser) VALUES (?, ?, ?, ?, ?)";

        return db.withConnection(conn -> {
            try {
                conn.setAutoCommit(false);

                int affectedRows = queryRunner.update(conn, sql,
                        battleReport.getNameBattleReport(),
                        battleReport.getDescriptionBattleReport(),
                        battleReport.getScenario_idScenario(),
                        battleReport.getArmyPoints(),
                        battleReport.getIdUser());

                if (affectedRows == 0) {
                    throw new SQLException("Creating BattleReport failed, no rows affected.");
                }

                int idBattleReport = queryRunner.query(conn, "SELECT LAST_INSERT_ID()", rs -> {
                    rs.next();
                    return rs.getInt(1);
                });

                // Insertion des joueurs
                if (battleReport.getPlayers() != null) {
                    for (Player player : battleReport.getPlayers()) {
                        player.setBattleReport_idBattleReport(idBattleReport);
                        PlayerRepository.create(player, conn);
                    }
                }

                // Insertion des photos
                if (battleReport.getBattleReportPhotos() != null) {
                    for (BattleReportPhoto photo : battleReport.getBattleReportPhotos()) {
                        photo.setBattleReport_idBattleReport(idBattleReport);
                        BattleReportPhotoRepository.create(photo, conn);
                    }
                }

                conn.commit();
                return idBattleReport;

            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException autoCommitEx) {
                    autoCommitEx.printStackTrace();
                }
            }
        });
    }


    public static int update(int id, BattleReport battleReport) throws SQLException {
        return db.withConnection(conn -> {
            try {
                conn.setAutoCommit(false);

                String sql = "UPDATE battlereport SET nameBattleReport = ?, descriptionBattleReport = ?, scenario_idScenario = ?, armyPoints = ?, idUser = ? WHERE idBattleReport = ?";
                int updatedRows = queryRunner.update(conn, sql,
                        battleReport.getNameBattleReport(),
                        battleReport.getDescriptionBattleReport(),
                        battleReport.getScenario_idScenario(),
                        battleReport.getArmyPoints(),
                        battleReport.getIdUser(),
                        id);

                // Supprimer les joueurs liés
                PlayerRepository.deleteByBattleReportId(id, conn);

                // Insérer les joueurs mis à jour
                if (battleReport.getPlayers() != null) {
                    for (Player player : battleReport.getPlayers()) {
                        player.setBattleReport_idBattleReport(id);
                        PlayerRepository.create(player, conn);
                    }
                }

                // Supprimer les photos liées
                BattleReportPhotoRepository.deleteByBattleReportId(id, conn);

                // Insérer les photos mises à jour
                if (battleReport.getBattleReportPhotos() != null) {
                    for (BattleReportPhoto photo : battleReport.getBattleReportPhotos()) {
                        photo.setBattleReport_idBattleReport(id);
                        BattleReportPhotoRepository.create(photo, conn);
                    }
                }

                conn.commit();
                return updatedRows;

            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException(e);

            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException autoCommitEx) {
                    autoCommitEx.printStackTrace();
                }
            }
        });
    }

    public static int delete(int id) throws SQLException {
        PlayerRepository.deleteByBattleReportId(id);
        BattleReportPhotoRepository.deleteByBattleReportId(id);

        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, "DELETE FROM battlereport WHERE idBattleReport = ?", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
