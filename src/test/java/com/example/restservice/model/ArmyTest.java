package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void testArmyConstructor() {
        Army army = new Army();
        assertNotNull(army);
    }

    @Test
    void testArmyConstructorWithParameters() {
        Army army = new Army(1, 2, 3, "Test Condition");
        assertNotNull(army);
        assertEquals(1, army.getArmyName_idArmyName());
        assertEquals(2, army.getArmyComposition_idArmyComposition());
        assertEquals(3, army.getUnitType_idUnitType());
        assertEquals("Test Condition", army.getUnitTypeCondition());
    }

    @Test
    void testSetAndGetArmyNameId() {
        Army army = new Army();
        army.setArmyName_idArmyName(5);
        assertEquals(5, army.getArmyName_idArmyName());
    }

    @Test
    void testSetAndGetArmyCompositionId() {
        Army army = new Army();
        army.setArmyComposition_idArmyComposition(10);
        assertEquals(10, army.getArmyComposition_idArmyComposition());
    }

    @Test
    void testSetAndGetUnitTypeId() {
        Army army = new Army();
        army.setUnitType_idUnitType(15);
        assertEquals(15, army.getUnitType_idUnitType());
    }

    @Test
    void testSetAndGetUnitTypeCondition() {
        Army army = new Army();
        army.setUnitTypeCondition("Special Condition");
        assertEquals("Special Condition", army.getUnitTypeCondition());
    }

    @Test
    void testArmyInitialValues() {
        Army army = new Army();
        assertEquals(0, army.getArmyName_idArmyName());
        assertEquals(0, army.getArmyComposition_idArmyComposition());
        assertEquals(0, army.getUnitType_idUnitType());
        assertNull(army.getUnitTypeCondition());
    }
}
