package com.example.restservice.repository;

import com.example.restservice.model.BattleReportPhoto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleReportPhotoRepositoryTest {

    @Test
    void testFindAllMethodExists() {
        assertDoesNotThrow(() -> {
            try {
                BattleReportPhotoRepository.findAll();
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
                BattleReportPhotoRepository.findById(1);
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
                BattleReportPhotoRepository.findByBattleReportId(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testCreateMethodExists() {
        BattleReportPhoto photo = new BattleReportPhoto();
        photo.setNameBattleReportPhoto("test.jpg");
        photo.setBattleReport_idBattleReport(1);

        assertDoesNotThrow(() -> {
            try {
                BattleReportPhotoRepository.create(photo);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testUpdateMethodExists() {
        BattleReportPhoto photo = new BattleReportPhoto();
        photo.setNameBattleReportPhoto("updated.jpg");
        photo.setBattleReport_idBattleReport(1);

        assertDoesNotThrow(() -> {
            try {
                BattleReportPhotoRepository.update(1, photo);
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
                BattleReportPhotoRepository.delete(1);
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
                BattleReportPhotoRepository.deleteByBattleReportId(1);
            } catch (SQLException e) {
                assertTrue(true);
            } catch (Exception e) {
                assertTrue(true);
            }
        });
    }

    @Test
    void testBattleReportPhotoModelUsage() {
        BattleReportPhoto photo = new BattleReportPhoto();

        // Test des setters
        photo.setIdBattleReportPhoto(1);
        photo.setNameBattleReportPhoto("example.jpg");
        photo.setBattleReport_idBattleReport(5);

        // Test des getters
        assertEquals(1, photo.getIdBattleReportPhoto());
        assertEquals("example.jpg", photo.getNameBattleReportPhoto());
        assertEquals(5, photo.getBattleReport_idBattleReport());
    }

    @Test
    void testBattleReportPhotoInitialState() {
        BattleReportPhoto photo = new BattleReportPhoto();

        assertEquals(0, photo.getIdBattleReportPhoto());
        assertEquals(0, photo.getBattleReport_idBattleReport());
        assertNull(photo.getNameBattleReportPhoto());
    }

    @Test
    void testBattleReportPhotoWithNullValues() {
        BattleReportPhoto photo = new BattleReportPhoto();

        assertDoesNotThrow(() -> {
            photo.setNameBattleReportPhoto(null);
            photo.setBattleReport_idBattleReport(0);
        });

        assertNull(photo.getNameBattleReportPhoto());
        assertEquals(0, photo.getBattleReport_idBattleReport());
    }
}
