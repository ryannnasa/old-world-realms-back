package com.example.restservice.controller;

import com.example.restservice.model.ArmyPhoto;
import com.example.restservice.repository.ArmyPhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArmyPhotoControllerTest {

    @InjectMocks
    private ArmyPhotoController armyPhotoController;

    @Test
    void testGetArmyPhotosSuccess() throws SQLException {
        ArmyPhoto photo1 = new ArmyPhoto();
        photo1.setIdArmyPhoto(1);
        photo1.setPhotoArmyName("space_marines_photo1.jpg");

        ArmyPhoto photo2 = new ArmyPhoto();
        photo2.setIdArmyPhoto(2);
        photo2.setPhotoArmyName("chaos_marines_photo1.jpg");

        List<ArmyPhoto> expectedPhotos = Arrays.asList(photo1, photo2);

        try (MockedStatic<ArmyPhotoRepository> mockedRepo = mockStatic(ArmyPhotoRepository.class)) {
            mockedRepo.when(ArmyPhotoRepository::findAll).thenReturn(expectedPhotos);

            List<ArmyPhoto> result = armyPhotoController.getArmyPhotos();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("space_marines_photo1.jpg", result.get(0).getPhotoArmyName());
            assertEquals("chaos_marines_photo1.jpg", result.get(1).getPhotoArmyName());
        }
    }

    @Test
    void testGetArmyPhotosWithSQLException() throws SQLException {
        try (MockedStatic<ArmyPhotoRepository> mockedRepo = mockStatic(ArmyPhotoRepository.class)) {
            mockedRepo.when(ArmyPhotoRepository::findAll).thenThrow(new SQLException("Database connection failed"));

            List<ArmyPhoto> result = armyPhotoController.getArmyPhotos();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetArmyPhotosEmptyResult() throws SQLException {
        try (MockedStatic<ArmyPhotoRepository> mockedRepo = mockStatic(ArmyPhotoRepository.class)) {
            mockedRepo.when(ArmyPhotoRepository::findAll).thenReturn(Collections.emptyList());

            List<ArmyPhoto> result = armyPhotoController.getArmyPhotos();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
