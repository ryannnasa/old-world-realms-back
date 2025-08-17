package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.repository.BattleReportRepository;
import com.example.restservice.service.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleReportControllerAdditionalTest {

    @Mock
    private S3Service s3Service;

    @Test
    void testGetBattleReportsWithMultipleResults() throws SQLException {
        BattleReport report1 = new BattleReport();
        report1.setIdBattleReport(1);
        report1.setNameBattleReport("Epic Battle 1");

        BattleReport report2 = new BattleReport();
        report2.setIdBattleReport(2);
        report2.setNameBattleReport("Epic Battle 2");

        List<BattleReport> expectedReports = Arrays.asList(report1, report2);

        try (MockedStatic<BattleReportRepository> mockedRepo = mockStatic(BattleReportRepository.class)) {
            mockedRepo.when(BattleReportRepository::findAll).thenReturn(expectedReports);

            BattleReportController controller = new BattleReportController(s3Service);
            List<BattleReport> result = controller.getBattleReports();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Epic Battle 1", result.get(0).getNameBattleReport());
            assertEquals("Epic Battle 2", result.get(1).getNameBattleReport());
        }
    }

    @Test
    void testGetBattleReportByIdFound() throws SQLException {
        BattleReport expectedReport = new BattleReport();
        expectedReport.setIdBattleReport(5);
        expectedReport.setNameBattleReport("Found Battle");

        try (MockedStatic<BattleReportRepository> mockedRepo = mockStatic(BattleReportRepository.class)) {
            mockedRepo.when(() -> BattleReportRepository.findById(5)).thenReturn(expectedReport);

            BattleReportController controller = new BattleReportController(s3Service);
            BattleReport result = controller.getBattleReport(5);

            assertNotNull(result);
            assertEquals(5, result.getIdBattleReport());
            assertEquals("Found Battle", result.getNameBattleReport());
        }
    }

    @Test
    void testCreateBattleReportSuccess() throws SQLException {
        BattleReport newReport = new BattleReport();
        newReport.setNameBattleReport("New Battle");
        newReport.setDescriptionBattleReport("Test Description");

        try (MockedStatic<BattleReportRepository> mockedRepo = mockStatic(BattleReportRepository.class)) {
            mockedRepo.when(() -> BattleReportRepository.create(any(BattleReport.class))).thenReturn(10);

            BattleReportController controller = new BattleReportController(s3Service);
            Object result = controller.createBattleReport(newReport);

            // Test simplement que la méthode ne lance pas d'exception
            assertDoesNotThrow(() -> {
                controller.createBattleReport(newReport);
            });
        }
    }

    @Test
    void testDeleteBattleReportSuccess() throws SQLException {
        try (MockedStatic<BattleReportRepository> mockedRepo = mockStatic(BattleReportRepository.class)) {
            mockedRepo.when(() -> BattleReportRepository.delete(1)).thenReturn(1);

            BattleReportController controller = new BattleReportController(s3Service);
            Object result = controller.deleteBattleReport(1);

            assertNotNull(result);
        }
    }
}
