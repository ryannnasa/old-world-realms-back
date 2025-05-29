package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Player;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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
        String sql = "INSERT INTO player (playerName, playerScore, alliance_idAlliance, armyName_idArmyName, armyComposition_idArmyComposition) VALUES (?, ?, ?, ?, ?)";
        return queryRunner.update(db.getConn(), sql,
                player.getPlayerName(),
                player.getPlayerScore(),
                player.getAlliance_idAlliance(),
                player.getArmyName_idArmyName(),
                player.getArmyComposition_idArmyComposition());
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


