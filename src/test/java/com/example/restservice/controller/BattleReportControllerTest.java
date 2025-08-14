package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportRepository;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BattleReportControllerTest {

    @Mock
    private S3Service s3Service;

    @InjectMocks
    private BattleReportController battleReportController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(battleReportController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getBattleReports_ShouldReturnListOfBattleReports() throws Exception {
        BattleReport battleReport1 = createTestBattleReport(1, "Battle 1");
        BattleReport battleReport2 = createTestBattleReport(2, "Battle 2");
        List<BattleReport> expectedReports = Arrays.asList(battleReport1, battleReport2);

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(BattleReportRepository::findAll).thenReturn(expectedReports);

            mockMvc.perform(get("/battlereport"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].nameBattleReport").value("Battle 1"))
                    .andExpect(jsonPath("$[1].nameBattleReport").value("Battle 2"));
        }
    }

    @Test
    void getBattleReports_WhenSQLException_ShouldReturnEmptyList() {
        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(BattleReportRepository::findAll).thenThrow(new SQLException("Database error"));

            List<BattleReport> result = battleReportController.getBattleReports();

            assertTrue(result.isEmpty());
        }
    }

    @Test
    void getBattleReport_WhenExists_ShouldReturnBattleReport() throws Exception {
        int battleReportId = 1;
        BattleReport expectedReport = createTestBattleReport(battleReportId, "Test Battle");

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.findById(battleReportId)).thenReturn(expectedReport);

            mockMvc.perform(get("/battlereport/{id}", battleReportId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.idBattleReport").value(battleReportId))
                    .andExpect(jsonPath("$.nameBattleReport").value("Test Battle"));
        }
    }

    @Test
    void getBattleReport_WhenNotFound_ShouldThrowException() {
        int battleReportId = 999;

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.findById(battleReportId)).thenThrow(new SQLException("Not found"));

            assertThrows(ResponseStatusException.class, () ->
                battleReportController.getBattleReport(battleReportId)
            );
        }
    }

    @Test
    void getBattleReportByUserId_ShouldReturnUserReports() {
        String userId = "user123";
        BattleReport battleReport = createTestBattleReport(1, "User Battle");
        battleReport.setIdUser(userId);
        List<BattleReport> expectedReports = Arrays.asList(battleReport);

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.findByUserId(userId)).thenReturn(expectedReports);

            List<BattleReport> result = battleReportController.getBattleReportByUserId(userId);

            assertEquals(1, result.size());
            assertEquals(userId, result.get(0).getIdUser());
        }
    }

    @Test
    void createBattleReport_ShouldReturnCreatedReport() {
        BattleReport inputReport = createTestBattleReport(0, "New Battle");
        BattleReport createdReport = createTestBattleReport(1, "New Battle");
        int generatedId = 1;

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.create(inputReport)).thenReturn(generatedId);
            repoMock.when(() -> BattleReportRepository.findById(generatedId)).thenReturn(createdReport);

            BattleReport result = battleReportController.createBattleReport(inputReport);

            assertNotNull(result);
            assertEquals(generatedId, result.getIdBattleReport());
            assertEquals("New Battle", result.getNameBattleReport());
        }
    }

    @Test
    void createBattleReport_WhenSQLException_ShouldThrowException() {
        BattleReport inputReport = createTestBattleReport(0, "New Battle");

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.create(inputReport)).thenThrow(new SQLException("Creation failed"));

            assertThrows(ResponseStatusException.class, () ->
                battleReportController.createBattleReport(inputReport)
            );
        }
    }

    @Test
    void updateBattleReport_WhenExists_ShouldReturnUpdatedReport() {
        int battleReportId = 1;
        BattleReport inputReport = createTestBattleReport(battleReportId, "Updated Battle");
        BattleReport updatedReport = createTestBattleReport(battleReportId, "Updated Battle");

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.update(battleReportId, inputReport)).thenReturn(1);
            repoMock.when(() -> BattleReportRepository.findById(battleReportId)).thenReturn(updatedReport);

            BattleReport result = battleReportController.updateBattleReport(battleReportId, inputReport);

            assertNotNull(result);
            assertEquals(battleReportId, result.getIdBattleReport());
            assertEquals("Updated Battle", result.getNameBattleReport());
        }
    }

    @Test
    void updateBattleReport_WhenNotFound_ShouldThrowException() {
        int battleReportId = 999;
        BattleReport inputReport = createTestBattleReport(battleReportId, "Updated Battle");

        try (MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {
            repoMock.when(() -> BattleReportRepository.update(battleReportId, inputReport)).thenReturn(0);

            assertThrows(ResponseStatusException.class, () ->
                battleReportController.updateBattleReport(battleReportId, inputReport)
            );
        }
    }

    @Test
    void deleteBattleReport_ShouldDeletePhotosAndReport() {
        int battleReportId = 1;
        BattleReportPhoto photo1 = new BattleReportPhoto();
        photo1.setNameBattleReportPhoto("photo1.jpg");
        BattleReportPhoto photo2 = new BattleReportPhoto();
        photo2.setNameBattleReportPhoto("photo2.jpg");
        List<BattleReportPhoto> photos = Arrays.asList(photo1, photo2);

        try (MockedStatic<BattleReportPhotoRepository> photoRepoMock = mockStatic(BattleReportPhotoRepository.class);
             MockedStatic<BattleReportRepository> repoMock = mockStatic(BattleReportRepository.class)) {

            photoRepoMock.when(() -> BattleReportPhotoRepository.findByBattleReportId(battleReportId))
                    .thenReturn(photos);
            repoMock.when(() -> BattleReportRepository.delete(battleReportId)).thenReturn(1);

            ResponseEntity<String> result = battleReportController.deleteBattleReport(battleReportId);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertTrue(result.getBody().contains("supprimés avec succès"));
            verify(s3Service, times(2)).deleteFile(anyString());
            verify(s3Service).deleteFile("photo1.jpg");
            verify(s3Service).deleteFile("photo2.jpg");
        }
    }

    @Test
    void uploadBattleReportPhotos_ShouldUploadFilesAndReturnFileNames() {
        int battleReportId = 1;
        MockMultipartFile file1 = new MockMultipartFile("file1", "image1.jpg", "image/jpeg", "content1".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file2", "image2.jpg", "image/jpeg", "content2".getBytes());
        MockMultipartFile[] files = {file1, file2};

        try (MockedStatic<BattleReportPhotoRepository> photoRepoMock = mockStatic(BattleReportPhotoRepository.class)) {
            photoRepoMock.when(() -> BattleReportPhotoRepository.create(any(BattleReportPhoto.class)))
                    .thenReturn(1);

            ResponseEntity<List<String>> result = battleReportController.uploadBattleReportPhotos(battleReportId, files);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
            assertEquals(2, result.getBody().size());
            verify(s3Service, times(2)).uploadFile(anyString(), any(), anyLong(), anyString());
        }
    }

    @Test
    void uploadBattleReportPhotos_WhenNoFiles_ShouldReturnBadRequest() {
        int battleReportId = 1;
        MockMultipartFile[] files = {};

        ResponseEntity<List<String>> result = battleReportController.uploadBattleReportPhotos(battleReportId, files);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody().get(0).contains("Aucun fichier reçu"));
    }

    @Test
    void deleteBattleReportPhotos_ShouldDeleteSpecifiedPhotos() {
        int battleReportId = 1;
        List<String> fileNamesToDelete = Arrays.asList("photo1.jpg", "photo2.jpg");

        BattleReportPhoto photo1 = new BattleReportPhoto();
        photo1.setIdBattleReportPhoto(1);
        photo1.setNameBattleReportPhoto("photo1.jpg");

        BattleReportPhoto photo2 = new BattleReportPhoto();
        photo2.setIdBattleReportPhoto(2);
        photo2.setNameBattleReportPhoto("photo2.jpg");

        try (MockedStatic<BattleReportPhotoRepository> photoRepoMock = mockStatic(BattleReportPhotoRepository.class)) {
            photoRepoMock.when(() -> BattleReportPhotoRepository.findByFileNameAndBattleReportId("photo1.jpg", battleReportId))
                    .thenReturn(photo1);
            photoRepoMock.when(() -> BattleReportPhotoRepository.findByFileNameAndBattleReportId("photo2.jpg", battleReportId))
                    .thenReturn(photo2);
            photoRepoMock.when(() -> BattleReportPhotoRepository.delete(anyInt())).thenReturn(1);

            ResponseEntity<String> result = battleReportController.deleteBattleReportPhotos(battleReportId, fileNamesToDelete);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertTrue(result.getBody().contains("supprimées avec succès"));
            verify(s3Service, times(2)).deleteFile(anyString());
        }
    }

    private BattleReport createTestBattleReport(int id, String name) {
        BattleReport battleReport = new BattleReport();
        battleReport.setIdBattleReport(id);
        battleReport.setNameBattleReport(name);
        battleReport.setDescriptionBattleReport("Test Description");
        battleReport.setScenario_idScenario(1);
        battleReport.setArmyPoints(1500);
        battleReport.setIdUser("testUser");
        return battleReport;
    }
}
