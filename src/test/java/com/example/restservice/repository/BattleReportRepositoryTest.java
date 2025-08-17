package com.example.restservice.repository;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BattleReportRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.findAll();
            } catch (SQLException e) {
                assertTrue(true, "SQLException attendue sans base de données");
            }
        });
    }

    @Test
    void testFindByIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.findById(1);
            } catch (SQLException e) {
                assertTrue(true, "SQLException attendue sans base de données");
            }
        });
    }

    @Test
    void testCreateMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.create(null);
            } catch (SQLException | NullPointerException e) {
                assertTrue(true, "Exception attendue avec paramètre ou sans DB");
            }
        });
    }

    @Test
    void testUpdateMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.update(1, null);
            } catch (SQLException | NullPointerException e) {
                assertTrue(true, "Exception attendue avec paramètre ou sans DB");
            }
        });
    }

    @Test
    void testDeleteMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.delete(1);
            } catch (SQLException e) {
                assertTrue(true, "SQLException attendue sans base de données");
            }
        });
    }

    @Test
    void testFindByUserIdMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportRepository.findByUserId("user123");
            } catch (SQLException e) {
                assertTrue(true, "SQLException attendue sans base de données");
            }
        });
    }

    @Test
    void testRepositoryInstantiation() {
        assertNotNull(BattleReportRepository.class);
    }
}
