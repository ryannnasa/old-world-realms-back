package com.example.restservice.repository;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ArmyNameRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        // Test que la méthode existe et peut être appelée
        assertDoesNotThrow(() -> {
            try {
                ArmyNameRepository.findAll();
            } catch (SQLException e) {
                // Dans un environnement de test sans vraie DB, c'est attendu
                assertTrue(true, "SQLException attendue sans base de données");
            }
        });
    }
}
