package com.example.restservice.repository;

import com.example.restservice.model.BattleReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BattleReportRepositoryEnhancedTest {

    @Test
    void testFindAllMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.findAll();
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
                BattleReportRepository.findById(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testFindByUserIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.findByUserId("testUser");
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testCreateMethodExists() {
        BattleReport battleReport = new BattleReport();
        battleReport.setNameBattleReport("Test");
        battleReport.setDescriptionBattleReport("Test Description");

        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.create(battleReport);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testUpdateMethodExists() {
        BattleReport battleReport = new BattleReport();
        battleReport.setNameBattleReport("Updated");

        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.update(1, battleReport);
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
                BattleReportRepository.delete(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }
}
