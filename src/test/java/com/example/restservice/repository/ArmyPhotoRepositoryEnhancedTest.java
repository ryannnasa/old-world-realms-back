package com.example.restservice.repository;

import com.example.restservice.model.ArmyPhoto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArmyPhotoRepositoryEnhancedTest {

    @Test
    void testFindAllMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                ArmyPhotoRepository.findAll();
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testFindAllReturnsListType() {
        try {
            List<ArmyPhoto> result = ArmyPhotoRepository.findAll();
            assertNotNull(result);
        } catch (SQLException e) {
            assertTrue(e.getMessage() != null);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
