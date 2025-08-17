package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArmyCompositionTest {

    @Test
    void testArmyCompositionConstructor() {
        ArmyComposition armyComposition = new ArmyComposition();
        assertNotNull(armyComposition);
    }

    @Test
    void testArmyCompositionConstructorWithParameters() {
        ArmyComposition armyComposition = new ArmyComposition(1, "Elite Army");
        assertEquals(1, armyComposition.getIdArmyComposition());
        assertEquals("Elite Army", armyComposition.getNameArmyComposition());
    }

    @Test
    void testSetAndGetIdArmyComposition() {
        ArmyComposition armyComposition = new ArmyComposition();
        armyComposition.setIdArmyComposition(1);
        assertEquals(1, armyComposition.getIdArmyComposition());
    }

    @Test
    void testSetAndGetNameArmyComposition() {
        ArmyComposition armyComposition = new ArmyComposition();
        armyComposition.setNameArmyComposition("Elite Army");
        assertEquals("Elite Army", armyComposition.getNameArmyComposition());
    }

    @Test
    void testArmyCompositionWithNullValues() {
        ArmyComposition armyComposition = new ArmyComposition();

        assertDoesNotThrow(() -> {
            armyComposition.setNameArmyComposition(null);
        });

        assertNull(armyComposition.getNameArmyComposition());
    }

    @Test
    void testArmyCompositionWithEmptyStrings() {
        ArmyComposition armyComposition = new ArmyComposition();

        armyComposition.setNameArmyComposition("");
        assertEquals("", armyComposition.getNameArmyComposition());
    }

    @Test
    void testArmyCompositionInitialState() {
        ArmyComposition armyComposition = new ArmyComposition();

        assertEquals(0, armyComposition.getIdArmyComposition());
        assertNull(armyComposition.getNameArmyComposition());
    }
}
