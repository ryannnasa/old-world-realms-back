package com.example.restservice.controller;

import com.example.restservice.model.Scenario;
import com.example.restservice.repository.ScenarioRepository;
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
class ScenarioControllerTest {

    @InjectMocks
    private ScenarioController scenarioController;

    @Test
    void testGetScenariosSuccess() throws SQLException {
        Scenario scenario1 = new Scenario();
        scenario1.setIdScenario(1);
        scenario1.setScenarioName("Battle of Horus Heresy");

        Scenario scenario2 = new Scenario();
        scenario2.setIdScenario(2);
        scenario2.setScenarioName("Siege of Terra");

        List<Scenario> expectedScenarios = Arrays.asList(scenario1, scenario2);

        try (MockedStatic<ScenarioRepository> mockedRepo = mockStatic(ScenarioRepository.class)) {
            mockedRepo.when(ScenarioRepository::findAll).thenReturn(expectedScenarios);

            List<Scenario> result = scenarioController.getScenarios();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Battle of Horus Heresy", result.get(0).getScenarioName());
            assertEquals("Siege of Terra", result.get(1).getScenarioName());
        }
    }

    @Test
    void testGetScenariosWithSQLException() throws SQLException {
        try (MockedStatic<ScenarioRepository> mockedRepo = mockStatic(ScenarioRepository.class)) {
            mockedRepo.when(ScenarioRepository::findAll).thenThrow(new SQLException("Database connection failed"));

            List<Scenario> result = scenarioController.getScenarios();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void testGetScenariosEmptyResult() throws SQLException {
        try (MockedStatic<ScenarioRepository> mockedRepo = mockStatic(ScenarioRepository.class)) {
            mockedRepo.when(ScenarioRepository::findAll).thenReturn(Collections.emptyList());

            List<Scenario> result = scenarioController.getScenarios();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
