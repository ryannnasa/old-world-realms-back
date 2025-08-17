package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerConstructor() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    void testPlayerConstructorWithParameters() {
        Player player = new Player(1, "Test Player", "Victory", 2, 3, 4, 5);
        assertNotNull(player);
        assertEquals(1, player.getIdPlayer());
        assertEquals("Test Player", player.getPlayerName());
        assertEquals("Victory", player.getPlayerScore());
        assertEquals(2, player.getAlliance_idAlliance());
    }

    @Test
    void testSetAndGetIdPlayer() {
        Player player = new Player();
        player.setIdPlayer(1);
        assertEquals(1, player.getIdPlayer());
    }

    @Test
    void testSetAndGetPlayerName() {
        Player player = new Player();
        player.setPlayerName("Test Player");
        assertEquals("Test Player", player.getPlayerName());
    }

    @Test
    void testSetAndGetPlayerScore() {
        Player player = new Player();
        player.setPlayerScore("Win");
        assertEquals("Win", player.getPlayerScore());
    }

    @Test
    void testSetAndGetAllianceId() {
        Player player = new Player();
        player.setAlliance_idAlliance(3);
        assertEquals(3, player.getAlliance_idAlliance());
    }

    @Test
    void testSetAndGetArmyNameId() {
        Player player = new Player();
        player.setArmyName_idArmyName(5);
        assertEquals(5, player.getArmyName_idArmyName());
    }

    @Test
    void testSetAndGetArmyCompositionId() {
        Player player = new Player();
        player.setArmyComposition_idArmyComposition(10);
        assertEquals(10, player.getArmyComposition_idArmyComposition());
    }

    @Test
    void testSetAndGetBattleReportId() {
        Player player = new Player();
        player.setBattleReport_idBattleReport(15);
        assertEquals(15, player.getBattleReport_idBattleReport());
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
}
