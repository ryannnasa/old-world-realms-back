package com.example.restservice.controller;

import com.example.restservice.model.Alliance;
import com.example.restservice.repository.AllianceRepository;
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
class AllianceControllerTest {

    @InjectMocks
    private AllianceController allianceController;

    @Test
    void testGetAlliancesSuccess() throws SQLException {
        Alliance alliance1 = new Alliance();
        alliance1.setIdAlliance(1);
        alliance1.setAllianceName("Imperium");

        Alliance alliance2 = new Alliance();
        alliance2.setIdAlliance(2);
        alliance2.setAllianceName("Chaos");

        List<Alliance> expectedAlliances = Arrays.asList(alliance1, alliance2);

        try (MockedStatic<AllianceRepository> mockedRepo = mockStatic(AllianceRepository.class)) {
            mockedRepo.when(AllianceRepository::findAll).thenReturn(expectedAlliances);

            List<Alliance> result = allianceController.getAlliances();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Imperium", result.get(0).getAllianceName());
            assertEquals("Chaos", result.get(1).getAllianceName());
        }
    }

    @Test
    void testGetAlliancesWithSQLException() throws SQLException {
        try (MockedStatic<AllianceRepository> mockedRepo = mockStatic(AllianceRepository.class)) {
            mockedRepo.when(AllianceRepository::findAll).thenThrow(new SQLException("Database error"));

            List<Alliance> result = allianceController.getAlliances();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetAlliancesEmptyResult() throws SQLException {
        try (MockedStatic<AllianceRepository> mockedRepo = mockStatic(AllianceRepository.class)) {
            mockedRepo.when(AllianceRepository::findAll).thenReturn(Collections.emptyList());

            List<Alliance> result = allianceController.getAlliances();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
