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

    public static List<Player> findAll() throws SQLException {
        ResultSetHandler<List<Player>> resultHandler = new BeanListHandler<>(Player.class);
        return queryRunner.query(db.getConn(),
                "SELECT * FROM player",
                resultHandler);
    }

    public static Player findById(int id) throws SQLException {
        ResultSetHandler<List<Player>> resultHandler = new BeanListHandler<>(Player.class);
        return queryRunner.query(db.getConn(),
        "SELECT * FROM player WHERE idPlayer =? ",
                id,resultHandler).getFirst();
    }

    public static int create(Player player) throws SQLException {
        Connection conn = db.getConn();
        String sql = "INSERT INTO Player (playerName, playerScore, alliance_idAlliance, armyName_idArmyName, armyComposition_idArmyComposition) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, player.getPlayerName());
        stmt.setString(2, player.getPlayerScore());
        stmt.setInt(3, player.getAlliance_idAlliance());
        stmt.setInt(4, player.getArmyName_idArmyName());
        stmt.setInt(5, player.getArmyComposition_idArmyComposition());

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

    public static int update(int id, Player player) throws SQLException {
        String sql = "UPDATE player SET playerName = ?, playerScore = ?, alliance_idAlliance = ?, armyName_idArmyName = ?, armyComposition_idArmyComposition = ? WHERE idPlayer = ?";
        return queryRunner.update(db.getConn(), sql,
                player.getPlayerName(),
                player.getPlayerScore(),
                player.getAlliance_idAlliance(),
                player.getArmyName_idArmyName(),
                player.getArmyComposition_idArmyComposition(),
                id);
    }

    public static int delete(int id) throws SQLException {
        return queryRunner.update(db.getConn(), "DELETE FROM player WHERE idPlayer = ?", id);
    }
}


