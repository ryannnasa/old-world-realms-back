package com.example.restservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArmyPhotoTest {

    @Test
    void testArmyPhotoConstructor() {
        ArmyPhoto armyPhoto = new ArmyPhoto();
        assertNotNull(armyPhoto);
    }

    @Test
    void testArmyPhotoConstructorWithParameters() {
        ArmyPhoto armyPhoto = new ArmyPhoto(1, 5, "army_image.jpg");
        assertEquals(1, armyPhoto.getIdArmyPhoto());
        assertEquals(5, armyPhoto.getArmyName_idArmyName());
        assertEquals("army_image.jpg", armyPhoto.getPhotoArmyName());
    }

    @Test
    void testSetAndGetIdArmyPhoto() {
        ArmyPhoto armyPhoto = new ArmyPhoto();
        armyPhoto.setIdArmyPhoto(1);
        assertEquals(1, armyPhoto.getIdArmyPhoto());
    }

    @Test
    void testSetAndGetPhotoArmyName() {
        ArmyPhoto armyPhoto = new ArmyPhoto();
        armyPhoto.setPhotoArmyName("army_image.jpg");
        assertEquals("army_image.jpg", armyPhoto.getPhotoArmyName());
    }

    @Test
    void testSetAndGetArmyNameId() {
        ArmyPhoto armyPhoto = new ArmyPhoto();
        armyPhoto.setArmyName_idArmyName(5);
        assertEquals(5, armyPhoto.getArmyName_idArmyName());
    }

    @Test
    void testArmyPhotoWithNullValues() {
        ArmyPhoto armyPhoto = new ArmyPhoto();

        assertDoesNotThrow(() -> {
            armyPhoto.setPhotoArmyName(null);
        });

        assertNull(armyPhoto.getPhotoArmyName());
    }

    @Test
    void testArmyPhotoInitialState() {
        ArmyPhoto armyPhoto = new ArmyPhoto();

        assertEquals(0, armyPhoto.getIdArmyPhoto());
        assertEquals(0, armyPhoto.getArmyName_idArmyName());
        assertNull(armyPhoto.getPhotoArmyName());
    }
}
