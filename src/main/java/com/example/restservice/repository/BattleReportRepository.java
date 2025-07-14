package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.BattleReport;
import com.example.restservice.model.Player;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BattleReportRepository {

    private static final QueryRunner queryRunner = new QueryRunner();
    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public static List<BattleReport> findAll() throws SQLException {
        return db.withConnection(conn -> {
            ResultSetHandler<List<BattleReport>> handler = new BeanListHandler<>(BattleReport.class);
            try {
                List<BattleReport> reports = queryRunner.query(conn, "SELECT * FROM battlereport", handler);

                // Pour chaque BattleReport, charger ses joueurs
                for (BattleReport report : reports) {
                    List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                    report.setPlayers(players);
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

                // Charger les joueurs associés
                List<Player> players = PlayerRepository.findByBattleReportId(report.getIdBattleReport());
                System.out.println("DEBUG: Players found for BattleReport " + report.getIdBattleReport() + " -> " + players.size());

                report.setPlayers(players);
                return report;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(BattleReport battleReport) throws SQLException {
        String sql = "INSERT INTO battlereport (nameBattleReport, descriptionBattleReport, battleReportPhoto_idBattleReportPhoto, scenario_idScenario, armyPoints) VALUES (?, ?, ?, ?, ?)";

        return db.withConnection(conn -> {
            try {
                int affectedRows = queryRunner.update(conn, sql,
                        battleReport.getNameBattleReport(),
                        battleReport.getDescriptionBattleReport(),
                        battleReport.getBattleReportPhoto_idBattleReportPhoto(),
                        battleReport.getScenario_idScenario(),
                        battleReport.getArmyPoints());

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
                        PlayerRepository.create(player); // Ce repository gère sa propre connexion
                    }
                }

                return idBattleReport;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int update(int id, BattleReport battleReport) throws SQLException {
        return db.withConnection(conn -> {
            try {
                conn.setAutoCommit(false);

                // Mise à jour du BattleReport
                String sql = "UPDATE battlereport SET nameBattleReport = ?, descriptionBattleReport = ?, battleReportPhoto_idBattleReportPhoto = ?, scenario_idScenario = ?, armyPoints = ? WHERE idBattleReport = ?";
                int updatedRows = queryRunner.update(conn, sql,
                        battleReport.getNameBattleReport(),
                        battleReport.getDescriptionBattleReport(),
                        battleReport.getBattleReportPhoto_idBattleReportPhoto(),
                        battleReport.getScenario_idScenario(),
                        battleReport.getArmyPoints(),
                        id);

                // Suppression des joueurs liés
                PlayerRepository.deleteByBattleReportId(id, conn);

                // Insertion des joueurs mis à jour
                if (battleReport.getPlayers() != null) {
                    for (Player player : battleReport.getPlayers()) {
                        player.setBattleReport_idBattleReport(id);
                        PlayerRepository.create(player, conn);
                    }
                }

                conn.commit();
                return updatedRows;

            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    // Log rollback exception, ne masque pas l'exception principale
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException(e);

            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException autoCommitEx) {
                    // Log erreur mais ne remplace pas l'exception principale
                    autoCommitEx.printStackTrace();
                }
            }
        });
    }



    public static int delete(int id) throws SQLException {
        // Supprimer d'abord les joueurs liés (PlayerRepository gère sa connexion)
        PlayerRepository.deleteByBattleReportId(id);

        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, "DELETE FROM battlereport WHERE idBattleReport = ?", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
