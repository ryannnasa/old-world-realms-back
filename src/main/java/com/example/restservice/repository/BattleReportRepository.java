package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import com.example.restservice.model.Player;
import com.example.restservice.model.BattleReportPhoto;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class BattleReportRepository {

    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    // Mapper de noms snake_case (SQL) vers camelCase (Java)
    private static final Map<String, String> columnToPropertyOverrides = new HashMap<>();
    static {
        columnToPropertyOverrides.put("idBattleReport", "idBattleReport");
        columnToPropertyOverrides.put("nameBattleReport", "nameBattleReport");
        columnToPropertyOverrides.put("descriptionBattleReport", "descriptionBattleReport");
        columnToPropertyOverrides.put("scenario_idScenario", "scenario_idScenario");
        columnToPropertyOverrides.put("armyPoints", "armyPoints");
        columnToPropertyOverrides.put("idUser", "idUser");
    }

    private static final RowProcessor rowProcessor =
            new BasicRowProcessor(new BeanProcessor(columnToPropertyOverrides));

    private static final ResultSetHandler<List<BattleReport>> battleReportListHandler =
            new BeanListHandler<>(BattleReport.class, rowProcessor);

    private static final QueryRunner queryRunner = new QueryRunner();

    public static List<BattleReport> findAll() throws SQLException {
        return db.withConnection(conn -> {
            try {
                List<BattleReport> reports = queryRunner.query(conn, "SELECT * FROM battlereport", battleReportListHandler);

                for (BattleReport report : reports) {
                    List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setPlayers(players);

                    List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                    List<String> fileNames = photos.stream()
                            .map(BattleReportPhoto::getNameBattleReportPhoto)
                            .toList();
                    report.setPhotoFileNames(fileNames);
                }

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

                List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                report.setPlayers(players);

                List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                List<String> fileNames = photos.stream()
                        .map(BattleReportPhoto::getNameBattleReportPhoto)
                        .toList();
                report.setPhotoFileNames(fileNames);

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
                    reports = new ArrayList<>();
                }

                for (BattleReport report : reports) {
                    List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setPlayers(players);

                    List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(report.getIdBattleReport());
                    List<String> photoFileNames = photos.stream()
                            .map(BattleReportPhoto::getNameBattleReportPhoto)
                            .collect(Collectors.toList());
                    report.setPhotoFileNames(photoFileNames);
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

                if (battleReport.getPlayers() != null) {
                    for (Player player : battleReport.getPlayers()) {
                        player.setBattleReport_idBattleReport(idBattleReport);
                        PlayerRepository.create(player, conn);
                    }
                }

                if (battleReport.getPhotoFileNames() != null) {
                    for (String fileName : battleReport.getPhotoFileNames()) {
                        BattleReportPhoto photo = new BattleReportPhoto();
                        photo.setBattleReport_idBattleReport(idBattleReport);
                        photo.setNameBattleReportPhoto(fileName);
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

                // Gestion des joueurs
                PlayerRepository.deleteByBattleReportId(id, conn);
                if (battleReport.getPlayers() != null) {
                    for (Player player : battleReport.getPlayers()) {
                        player.setBattleReport_idBattleReport(id);
                        PlayerRepository.create(player, conn);
                    }
                }

                // Gestion intelligente des photos
                List<BattleReportPhoto> existingPhotos = BattleReportPhotoRepository.findByBattleReportId(id);
                List<String> existingFileNames = existingPhotos.stream()
                        .map(BattleReportPhoto::getNameBattleReportPhoto)
                        .collect(Collectors.toList());

                List<String> newFileNames = battleReport.getPhotoFileNames() != null ?
                        battleReport.getPhotoFileNames() : new ArrayList<>();

                // Ne supprimer que si la liste newFileNames n'est pas vide
                if (!newFileNames.isEmpty()) {
                    // Supprimer les photos qui ne sont plus dans la nouvelle liste
                    for (BattleReportPhoto existingPhoto : existingPhotos) {
                        if (!newFileNames.contains(existingPhoto.getNameBattleReportPhoto())) {
                            queryRunner.update(conn, "DELETE FROM battlereportphoto WHERE idBattleReportPhoto = ?",
                                    existingPhoto.getIdBattleReportPhoto());
                        }
                    }

                    // Ajouter les nouvelles photos qui n'existaient pas
                    for (String fileName : newFileNames) {
                        if (!existingFileNames.contains(fileName)) {
                            BattleReportPhoto photo = new BattleReportPhoto();
                            photo.setBattleReport_idBattleReport(id);
                            photo.setNameBattleReportPhoto(fileName);
                            BattleReportPhotoRepository.create(photo, conn);
                        }
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
