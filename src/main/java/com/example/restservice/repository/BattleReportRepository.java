package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import com.example.restservice.model.Player;
import com.example.restservice.model.BattleReportPhoto;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.*;

public class BattleReportRepository {

    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    // Handler simplifié sans mapping inutile
    private static final ResultSetHandler<List<BattleReport>> battleReportListHandler =
            new BeanListHandler<>(BattleReport.class);

    private static final QueryRunner queryRunner = new QueryRunner();

    // Méthode utilitaire pour enrichir un BattleReport avec ses players et photos
    private static void enrichBattleReport(BattleReport report) throws SQLException {
        List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
        report.setPlayers(players);

        List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
        List<String> fileNames = photos.stream()
                .map(BattleReportPhoto::getNameBattleReportPhoto)
                .toList();
        report.setPhotoFileNames(fileNames);
    }

    public static List<BattleReport> findAll() throws SQLException {
        return db.withConnection(conn -> {
            try {
                List<BattleReport> reports = queryRunner.query(conn, "SELECT * FROM battlereport", battleReportListHandler);

                reports.forEach(report -> {
                    try {
                        enrichBattleReport(report);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                return reports;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static BattleReport findById(int id) throws SQLException {
        return db.withConnection(conn -> {
            try {
                List<BattleReport> reports = queryRunner.query(conn,
                        "SELECT * FROM battlereport WHERE idBattleReport = ?",
                        battleReportListHandler,
                        id);

                if (reports == null || reports.isEmpty()) {
                    return null;
                }

                BattleReport report = reports.get(0);
                enrichBattleReport(report);
                return report;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static List<BattleReport> findByUserId(String idUser) throws SQLException {
        return db.withConnection(conn -> {
            try {
                List<BattleReport> reports = queryRunner.query(conn,
                        "SELECT * FROM battlereport WHERE idUser = ?",
                        battleReportListHandler,
                        idUser);

                if (reports == null) {
                    return new ArrayList<>();
                }

                reports.forEach(report -> {
                    try {
                        enrichBattleReport(report);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

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

                // Simplification avec Optional et forEach
                Optional.ofNullable(battleReport.getPlayers())
                        .ifPresent(players -> players.forEach(player -> {
                            try {
                                player.setBattleReport_idBattleReport(idBattleReport);
                                PlayerRepository.create(player, conn);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }));

                Optional.ofNullable(battleReport.getPhotoFileNames())
                        .ifPresent(fileNames -> fileNames.forEach(fileName -> {
                            try {
                                BattleReportPhoto photo = new BattleReportPhoto();
                                photo.setBattleReport_idBattleReport(idBattleReport);
                                photo.setNameBattleReportPhoto(fileName);
                                BattleReportPhotoRepository.create(photo, conn);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }));

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

                // Gestion des joueurs avec Optional et forEach
                PlayerRepository.deleteByBattleReportId(id, conn);
                Optional.ofNullable(battleReport.getPlayers())
                        .ifPresent(players -> players.forEach(player -> {
                            try {
                                player.setBattleReport_idBattleReport(id);
                                PlayerRepository.create(player, conn);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }));

                // Gestion intelligente des photos simplifiée
                List<BattleReportPhoto> existingPhotos = BattleReportPhotoRepository.findByBattleReportId(id);
                List<String> existingFileNames = existingPhotos.stream()
                        .map(BattleReportPhoto::getNameBattleReportPhoto)
                        .toList();

                List<String> newFileNames = Optional.ofNullable(battleReport.getPhotoFileNames())
                        .orElse(new ArrayList<>());

                // Ne supprimer que si la liste newFileNames n'est pas vide
                if (!newFileNames.isEmpty()) {
                    // Supprimer les photos qui ne sont plus dans la nouvelle liste
                    existingPhotos.stream()
                            .filter(photo -> !newFileNames.contains(photo.getNameBattleReportPhoto()))
                            .forEach(photo -> {
                                try {
                                    queryRunner.update(conn, "DELETE FROM battlereportphoto WHERE idBattleReportPhoto = ?",
                                            photo.getIdBattleReportPhoto());
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                    // Ajouter les nouvelles photos qui n'existaient pas
                    newFileNames.stream()
                            .filter(fileName -> !existingFileNames.contains(fileName))
                            .forEach(fileName -> {
                                try {
                                    BattleReportPhoto photo = new BattleReportPhoto();
                                    photo.setBattleReport_idBattleReport(id);
                                    photo.setNameBattleReportPhoto(fileName);
                                    BattleReportPhotoRepository.create(photo, conn);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });
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
        return db.withConnection(conn -> {
            try {
                conn.setAutoCommit(false);

                // Supprimer d'abord les dépendances dans une transaction
                PlayerRepository.deleteByBattleReportId(id, conn);
                BattleReportPhotoRepository.deleteByBattleReportId(id, conn);

                // Puis supprimer le rapport principal
                int result = queryRunner.update(conn, "DELETE FROM battlereport WHERE idBattleReport = ?", id);

                conn.commit();
                return result;

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
}
