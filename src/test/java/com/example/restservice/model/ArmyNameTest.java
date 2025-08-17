package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArmyNameTest {

    @Test
    void testArmyNameConstructor() {
        ArmyName armyName = new ArmyName();
        assertNotNull(armyName);
    }

    @Test
    void testArmyNameConstructorWithParameters() {
        ArmyName armyName = new ArmyName(1, "Space Marines");
        assertEquals(1, armyName.getIdArmyName());
        assertEquals("Space Marines", armyName.getNameArmyName());
    }

    @Test
    void testSetAndGetIdArmyName() {
        ArmyName armyName = new ArmyName();
        armyName.setIdArmyName(1);
        assertEquals(1, armyName.getIdArmyName());
    }

    @Test
    void testSetAndGetNameArmyName() {
        ArmyName armyName = new ArmyName();
        armyName.setNameArmyName("Space Marines");
        assertEquals("Space Marines", armyName.getNameArmyName());
    }

    @Test
    void testArmyNameWithNullValues() {
        ArmyName armyName = new ArmyName();

        assertDoesNotThrow(() -> {
            armyName.setNameArmyName(null);
        });

        assertNull(armyName.getNameArmyName());
    }

    @Test
    void testArmyNameInitialState() {
        ArmyName armyName = new ArmyName();

        assertEquals(0, armyName.getIdArmyName());
        assertNull(armyName.getNameArmyName());
    }
}
