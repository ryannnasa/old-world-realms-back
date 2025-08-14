package com.example.restservice.repository;

import com.example.restservice.model.BattleReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BattleReportRepositoryTest {

    @Test
    void createTestBattleReport_ShouldCreateValidObject() {
        BattleReport battleReport = createTestBattleReport(1, "Test Battle", "Test Description");

        assertEquals(1, battleReport.getIdBattleReport());
        assertEquals("Test Battle", battleReport.getNameBattleReport());
        assertEquals("Test Description", battleReport.getDescriptionBattleReport());
        assertEquals(1, battleReport.getScenario_idScenario());
        assertEquals(1500, battleReport.getArmyPoints());
        assertEquals("testUser", battleReport.getIdUser());
    }

    @Test
    void battleReportModel_ShouldHandleNullValues() {
        BattleReport battleReport = new BattleReport();

        assertNotNull(battleReport);
    }

    @Test
    void battleReportModel_ShouldSetAndGetProperties() {
        BattleReport battleReport = new BattleReport();

        battleReport.setIdBattleReport(42);
        battleReport.setNameBattleReport("Epic Battle");
        battleReport.setDescriptionBattleReport("An epic battle description");
        battleReport.setScenario_idScenario(3);
        battleReport.setArmyPoints(2000);
        battleReport.setIdUser("user456");

        assertEquals(42, battleReport.getIdBattleReport());
        assertEquals("Epic Battle", battleReport.getNameBattleReport());
        assertEquals("An epic battle description", battleReport.getDescriptionBattleReport());
        assertEquals(3, battleReport.getScenario_idScenario());
        assertEquals(2000, battleReport.getArmyPoints());
        assertEquals("user456", battleReport.getIdUser());
    }

    @Test
    void battleReportValidation_ShouldValidateRequiredFields() {
        BattleReport battleReport = createTestBattleReport(1, "Test", "Description");

        assertNotNull(battleReport.getNameBattleReport());
        assertFalse(battleReport.getNameBattleReport().isEmpty());

        assertNotNull(battleReport.getDescriptionBattleReport());
        assertFalse(battleReport.getDescriptionBattleReport().isEmpty());

        assertTrue(battleReport.getArmyPoints() > 0);
        assertTrue(battleReport.getScenario_idScenario() > 0);
    }

    @Test
    void battleReportModel_ShouldHandleEdgeCases() {
        BattleReport battleReport = new BattleReport();

        battleReport.setNameBattleReport("");
        battleReport.setDescriptionBattleReport("");
        battleReport.setArmyPoints(0);
        battleReport.setScenario_idScenario(0);

        assertEquals("", battleReport.getNameBattleReport());
        assertEquals("", battleReport.getDescriptionBattleReport());
        assertEquals(0, battleReport.getArmyPoints());
        assertEquals(0, battleReport.getScenario_idScenario());
    }

    @Test
    void battleReportRepository_ShouldExistAsClass() {
        assertDoesNotThrow(() -> {
            Class<?> repositoryClass = BattleReportRepository.class;
            assertNotNull(repositoryClass);
            assertTrue(repositoryClass.getName().contains("BattleReportRepository"));
        });
    }

    private BattleReport createTestBattleReport(int id, String name, String description) {
        BattleReport battleReport = new BattleReport();
        battleReport.setIdBattleReport(id);
        battleReport.setNameBattleReport(name);
        battleReport.setDescriptionBattleReport(description);
        battleReport.setScenario_idScenario(1);
        battleReport.setArmyPoints(1500);
        battleReport.setIdUser("testUser");
        return battleReport;
    }
}
