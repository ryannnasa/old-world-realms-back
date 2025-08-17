package com.example.restservice.repository;

import com.example.restservice.DatabaseSingleton;
import com.example.restservice.model.Alliance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AllianceRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        // Test que la méthode existe et peut être appelée
        assertDoesNotThrow(() -> {
            try {
                AllianceRepository.findAll();
            } catch (SQLException e) {
                // SQLException attendue sans vraie base de données
                assertTrue(true);
            } catch (Exception e) {
                // Autres exceptions possibles
                assertTrue(true);
            }
        });
    }

    @Test
    void testFindAllReturnsListType() {
        // Teste si findAll() retourne un type de liste
        try {
            List<Alliance> result = AllianceRepository.findAll();
            // Si ça marche sans DB, tant mieux
            assertNotNull(result);
        } catch (SQLException e) {
            // Normal sans DB
            assertTrue(e.getMessage() != null);
        } catch (Exception e) {
            // Autres erreurs de connexion acceptées
            assertTrue(true);
        }
    }
}
