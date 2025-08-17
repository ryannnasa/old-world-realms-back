package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class BattleReportTest {

    @Test
    void testBattleReportConstructor() {
        BattleReport battleReport = new BattleReport();
        assertNotNull(battleReport);
    }

    @Test
    void testSetAndGetIdBattleReport() {
        BattleReport battleReport = new BattleReport();
        battleReport.setIdBattleReport(1);
        assertEquals(1, battleReport.getIdBattleReport());
    }

    @Test
    void testSetAndGetNameBattleReport() {
        BattleReport battleReport = new BattleReport();
        battleReport.setNameBattleReport("Test Battle");
        assertEquals("Test Battle", battleReport.getNameBattleReport());
    }

    @Test
    void testSetAndGetDescriptionBattleReport() {
        BattleReport battleReport = new BattleReport();
        battleReport.setDescriptionBattleReport("Description");
        assertEquals("Description", battleReport.getDescriptionBattleReport());
    }

    @Test
    void testSetAndGetScenarioId() {
        BattleReport battleReport = new BattleReport();
        battleReport.setScenario_idScenario(5);
        assertEquals(5, battleReport.getScenario_idScenario());
    }

    @Test
    void testSetAndGetArmyPoints() {
        BattleReport battleReport = new BattleReport();
        battleReport.setArmyPoints(2000);
        assertEquals(2000, battleReport.getArmyPoints());
    }

    @Test
    void testSetAndGetIdUser() {
        BattleReport battleReport = new BattleReport();
        battleReport.setIdUser("user123");
        assertEquals("user123", battleReport.getIdUser());
    }

    @Test
    void testSetAndGetPlayers() {
        BattleReport battleReport = new BattleReport();
        List<Player> players = new ArrayList<>();
        Player player = new Player();
        players.add(player);

        battleReport.setPlayers(players);
        assertEquals(players, battleReport.getPlayers());
        assertEquals(1, battleReport.getPlayers().size());
    }

    @Test
    void testSetAndGetPhotoFileNames() {
        BattleReport battleReport = new BattleReport();
        List<String> photoFileNames = new ArrayList<>();
        photoFileNames.add("photo1.jpg");
        photoFileNames.add("photo2.jpg");

        battleReport.setPhotoFileNames(photoFileNames);
        assertEquals(photoFileNames, battleReport.getPhotoFileNames());
        assertEquals(2, battleReport.getPhotoFileNames().size());
    }

    @Test
    void testBattleReportWithInitialNullValues() {
        BattleReport battleReport = new BattleReport();
        // Test que les valeurs initiales sont correctes (null ou 0)
        assertEquals(0, battleReport.getIdBattleReport());
        assertEquals(0, battleReport.getScenario_idScenario());
        assertEquals(0, battleReport.getArmyPoints());
    }
}
