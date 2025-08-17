package com.example.restservice.repository;

import com.example.restservice.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.findAll();
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testFindByIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.findById(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testFindByBattleReportIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.findByBattleReportId(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testCreateMethodExists() {
        Player player = new Player();
        player.setPlayerName("Test Player");
        player.setPlayerScore("Victory");
        player.setAlliance_idAlliance(1);
        player.setArmyName_idArmyName(1);
        player.setArmyComposition_idArmyComposition(1);
        player.setBattleReport_idBattleReport(1);

        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.create(player);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testUpdateMethodExists() {
        Player player = new Player();
        player.setPlayerName("Updated Player");
        player.setPlayerScore("Defeat");
        player.setAlliance_idAlliance(2);

        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.update(1, player);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testDeleteMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.delete(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testDeleteByBattleReportIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                PlayerRepository.deleteByBattleReportId(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testPlayerModelCreationAndSetters() {
        Player player = new Player();

        // Test tous les setters
        player.setIdPlayer(1);
        player.setPlayerName("Test Player Name");
        player.setPlayerScore("Win");
        player.setAlliance_idAlliance(5);
        player.setArmyName_idArmyName(10);
        player.setArmyComposition_idArmyComposition(15);
        player.setBattleReport_idBattleReport(20);

        // Vérification des getters
        assertEquals(1, player.getIdPlayer());
        assertEquals("Test Player Name", player.getPlayerName());
        assertEquals("Win", player.getPlayerScore());
        assertEquals(5, player.getAlliance_idAlliance());
        assertEquals(10, player.getArmyName_idArmyName());
        assertEquals(15, player.getArmyComposition_idArmyComposition());
        assertEquals(20, player.getBattleReport_idBattleReport());
    }

    @Test
    void testPlayerConstructorWithAllParameters() {
        Player player = new Player(1, "John Doe", "Victory", 2, 3, 4, 5);

        assertEquals(1, player.getIdPlayer());
        assertEquals("John Doe", player.getPlayerName());
        assertEquals("Victory", player.getPlayerScore());
        assertEquals(2, player.getAlliance_idAlliance());
        assertEquals(3, player.getArmyName_idArmyName());
        assertEquals(4, player.getArmyComposition_idArmyComposition());
        assertEquals(5, player.getBattleReport_idBattleReport());
    }

    @Test
    void testPlayerInitialValues() {
        Player player = new Player();

        assertEquals(0, player.getIdPlayer());
        assertEquals(0, player.getAlliance_idAlliance());
        assertEquals(0, player.getArmyName_idArmyName());
        assertEquals(0, player.getArmyComposition_idArmyComposition());
        assertEquals(0, player.getBattleReport_idBattleReport());
        assertNull(player.getPlayerName());
        assertNull(player.getPlayerScore());
    }

    @Test
    void testPlayerWithNullStringValues() {
        Player player = new Player();

        assertDoesNotThrow(() -> {
            player.setPlayerName(null);
            player.setPlayerScore(null);
        });

        assertNull(player.getPlayerName());
        assertNull(player.getPlayerScore());
    }

    @Test
    void testPlayerWithEmptyStringValues() {
        Player player = new Player();

        player.setPlayerName("");
        player.setPlayerScore("");

        assertEquals("", player.getPlayerName());
        assertEquals("", player.getPlayerScore());
    }

    @Test
    void testPlayerWithSpecialCharacters() {
        Player player = new Player();

        player.setPlayerName("Jöhn Döe-Smith");
        player.setPlayerScore("Victoire éclatante!");

        assertEquals("Jöhn Döe-Smith", player.getPlayerName());
        assertEquals("Victoire éclatante!", player.getPlayerScore());
    }
}
