package com.example.restservice.controller;

import com.example.restservice.model.ArmyName;
import com.example.restservice.repository.ArmyNameRepository;
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
class ArmyNameControllerTest {

    @InjectMocks
    private ArmyNameController armyNameController;

    @Test
    void testGetArmiesNameSuccess() throws SQLException {
        ArmyName armyName1 = new ArmyName();
        armyName1.setIdArmyName(1);
        armyName1.setNameArmyName("Space Marines");

        ArmyName armyName2 = new ArmyName();
        armyName2.setIdArmyName(2);
        armyName2.setNameArmyName("Chaos Space Marines");

        List<ArmyName> expectedNames = Arrays.asList(armyName1, armyName2);

        try (MockedStatic<ArmyNameRepository> mockedRepo = mockStatic(ArmyNameRepository.class)) {
            mockedRepo.when(ArmyNameRepository::findAll).thenReturn(expectedNames);

            List<ArmyName> result = armyNameController.getArmiesName();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Space Marines", result.get(0).getNameArmyName());
            assertEquals("Chaos Space Marines", result.get(1).getNameArmyName());
        }
    }

    @Test
    void testGetArmiesNameWithSQLException() throws SQLException {
        try (MockedStatic<ArmyNameRepository> mockedRepo = mockStatic(ArmyNameRepository.class)) {
            mockedRepo.when(ArmyNameRepository::findAll).thenThrow(new SQLException("Database error"));

            List<ArmyName> result = armyNameController.getArmiesName();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetArmiesNameEmptyResult() throws SQLException {
        try (MockedStatic<ArmyNameRepository> mockedRepo = mockStatic(ArmyNameRepository.class)) {
            mockedRepo.when(ArmyNameRepository::findAll).thenReturn(Collections.emptyList());

            List<ArmyName> result = armyNameController.getArmiesName();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
