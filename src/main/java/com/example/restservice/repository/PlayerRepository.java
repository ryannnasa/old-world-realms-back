package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Player;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.List;

public class PlayerRepository {

    private static final QueryRunner queryRunner = new QueryRunner();
    private static final DatabaseSingleton db = DatabaseSingleton.getInstance();

    private static final ResultSetHandler<List<Player>> listHandler =
            new BeanListHandler<>(Player.class);

    public static List<Player> findAll() throws SQLException {
        return db.withConnection(conn -> {
            try {
                return queryRunner.query(conn, "SELECT * FROM player", listHandler);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Player findById(int id) throws SQLException {
        return db.withConnection(conn -> {
            try {
                List<Player> players = queryRunner.query(conn, "SELECT * FROM player WHERE idPlayer = ?", listHandler, id);
                return players.isEmpty() ? null : players.get(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static List<Player> findByBattleReportId(int idBattleReport) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return queryRunner.query(conn, "SELECT * FROM player WHERE battleReport_idBattleReport = ?", listHandler, idBattleReport);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(Player player) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return create(player, conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int create(Player player, Connection conn) throws SQLException {
        String sql = "INSERT INTO player (playerName, playerScore, alliance_idAlliance, armyName_idArmyName, armyComposition_idArmyComposition, battleReport_idBattleReport) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, player.getPlayerName());
            stmt.setString(2, player.getPlayerScore());
            stmt.setInt(3, player.getAlliance_idAlliance());
            stmt.setInt(4, player.getArmyName_idArmyName());
            stmt.setInt(5, player.getArmyComposition_idArmyComposition());
            stmt.setInt(6, player.getBattleReport_idBattleReport());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating player failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating player failed, no ID obtained.");
                }
            }
        }
    }

    public static int update(int id, Player player) throws SQLException {
        String sql = "UPDATE player SET playerName = ?, playerScore = ?, alliance_idAlliance = ?, armyName_idArmyName = ?, armyComposition_idArmyComposition = ?, battleReport_idBattleReport = ? WHERE idPlayer = ?";

        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, sql,
                        player.getPlayerName(),
                        player.getPlayerScore(),
                        player.getAlliance_idAlliance(),
                        player.getArmyName_idArmyName(),
                        player.getArmyComposition_idArmyComposition(),
                        player.getBattleReport_idBattleReport(),
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
                "DELETE FROM player WHERE battleReport_idBattleReport = ?",
                battleReportId);
    }

    public static int delete(int id) throws SQLException {
        return db.withConnection(conn -> {
            try {
                return queryRunner.update(conn, "DELETE FROM player WHERE idPlayer = ?", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
