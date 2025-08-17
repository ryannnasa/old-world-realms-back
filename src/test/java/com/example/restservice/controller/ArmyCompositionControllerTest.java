package com.example.restservice.controller;

import com.example.restservice.model.ArmyComposition;
import com.example.restservice.repository.ArmyCompositionRepository;
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
class ArmyCompositionControllerTest {

    @InjectMocks
    private ArmyCompositionController armyCompositionController;

    @Test
    void testGetArmiesCompositionSuccess() throws SQLException {
        ArmyComposition composition1 = new ArmyComposition();
        composition1.setIdArmyComposition(1);
        composition1.setNameArmyComposition("Elite Force");

        ArmyComposition composition2 = new ArmyComposition();
        composition2.setIdArmyComposition(2);
        composition2.setNameArmyComposition("Heavy Support");

        List<ArmyComposition> expectedCompositions = Arrays.asList(composition1, composition2);

        try (MockedStatic<ArmyCompositionRepository> mockedRepo = mockStatic(ArmyCompositionRepository.class)) {
            mockedRepo.when(ArmyCompositionRepository::findAll).thenReturn(expectedCompositions);

            List<ArmyComposition> result = armyCompositionController.getArmiesComposition();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(1, result.get(0).getIdArmyComposition());
            assertEquals(2, result.get(1).getIdArmyComposition());
        }
    }

    @Test
    void testGetArmiesCompositionWithSQLException() throws SQLException {
        try (MockedStatic<ArmyCompositionRepository> mockedRepo = mockStatic(ArmyCompositionRepository.class)) {
            mockedRepo.when(ArmyCompositionRepository::findAll).thenThrow(new SQLException("Database error"));

            List<ArmyComposition> result = armyCompositionController.getArmiesComposition();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetArmiesCompositionEmptyResult() throws SQLException {
        try (MockedStatic<ArmyCompositionRepository> mockedRepo = mockStatic(ArmyCompositionRepository.class)) {
            mockedRepo.when(ArmyCompositionRepository::findAll).thenReturn(Collections.emptyList());

            List<ArmyComposition> result = armyCompositionController.getArmiesComposition();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
