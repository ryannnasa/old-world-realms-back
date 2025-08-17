package com.example.restservice.controller;

import com.example.restservice.model.Army;
import com.example.restservice.repository.ArmyRepository;
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
class ArmyControllerTest {

    @InjectMocks
    private ArmyController armyController;

    @Test
    void testGetArmiesSuccess() throws SQLException {
        Army army1 = new Army();
        army1.setArmyName_idArmyName(1);
        army1.setUnitTypeCondition("Elite");

        Army army2 = new Army();
        army2.setArmyName_idArmyName(2);
        army2.setUnitTypeCondition("Heavy Support");

        List<Army> expectedArmies = Arrays.asList(army1, army2);

        try (MockedStatic<ArmyRepository> mockedRepo = mockStatic(ArmyRepository.class)) {
            mockedRepo.when(ArmyRepository::findAll).thenReturn(expectedArmies);

            List<Army> result = armyController.getArmies();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(1, result.get(0).getArmyName_idArmyName());
            assertEquals(2, result.get(1).getArmyName_idArmyName());
        }
    }

    @Test
    void testGetArmiesWithSQLException() throws SQLException {
        try (MockedStatic<ArmyRepository> mockedRepo = mockStatic(ArmyRepository.class)) {
            mockedRepo.when(ArmyRepository::findAll).thenThrow(new SQLException("Database error"));

            List<Army> result = armyController.getArmies();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetArmiesEmptyResult() throws SQLException {
        try (MockedStatic<ArmyRepository> mockedRepo = mockStatic(ArmyRepository.class)) {
            mockedRepo.when(ArmyRepository::findAll).thenReturn(Collections.emptyList());

            List<Army> result = armyController.getArmies();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
