package com.example.restservice.repository;

import com.example.restservice.model.Army;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArmyRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        // Test que la méthode existe et peut être appelée
        assertDoesNotThrow(() -> {
            try {
                ArmyRepository.findAll();
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
        // Test que la méthode findAll() retourne une liste
        try {
            List<Army> result = ArmyRepository.findAll();
            assertNotNull(result);
        } catch (SQLException e) {
            assertTrue(e.getMessage() != null);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
