package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    @Test
    void testScenarioConstructor() {
        Scenario scenario = new Scenario();
        assertNotNull(scenario);
    }

    @Test
    void testScenarioConstructorWithParameters() {
        Scenario scenario = new Scenario(1, "Test Scenario");
        assertNotNull(scenario);
        assertEquals(1, scenario.getIdScenario());
        assertEquals("Test Scenario", scenario.getScenarioName());
    }

    @Test
    void testSetAndGetIdScenario() {
        Scenario scenario = new Scenario();
        scenario.setIdScenario(1);
        assertEquals(1, scenario.getIdScenario());
    }

    @Test
    void testSetAndGetScenarioName() {
        Scenario scenario = new Scenario();
        scenario.setScenarioName("Test Scenario");
        assertEquals("Test Scenario", scenario.getScenarioName());
    }

    @Test
    void testScenarioInitialValues() {
        Scenario scenario = new Scenario();
        assertEquals(0, scenario.getIdScenario());
        assertNull(scenario.getScenarioName());
    }
}
