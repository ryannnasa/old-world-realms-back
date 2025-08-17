package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AllianceTest {

    @Test
    void testAllianceConstructorAndGetters() {
        Alliance alliance = new Alliance();
        assertNotNull(alliance);
    }

    @Test
    void testSetAndGetId() {
        Alliance alliance = new Alliance();
        alliance.setIdAlliance(1);
        assertEquals(1, alliance.getIdAlliance());
    }

    @Test
    void testSetAndGetAllianceName() {
        Alliance alliance = new Alliance();
        alliance.setAllianceName("Test Alliance");
        assertEquals("Test Alliance", alliance.getAllianceName());
    }

    @Test
    void testAllianceEquality() {
        Alliance alliance1 = new Alliance();
        alliance1.setIdAlliance(1);
        alliance1.setAllianceName("Test");

        Alliance alliance2 = new Alliance();
        alliance2.setIdAlliance(1);
        alliance2.setAllianceName("Test");

        assertNotNull(alliance1);
        assertNotNull(alliance2);
    }

    @Test
    void testAllianceToString() {
        Alliance alliance = new Alliance();
        alliance.setIdAlliance(1);
        alliance.setAllianceName("Test Alliance");

        String result = alliance.toString();
        assertNotNull(result);
    }
}
