package com.example.restservice.controller;

import com.example.restservice.model.ArmyPhoto;
import com.example.restservice.repository.ArmyPhotoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ArmyPhotoController {
    // Yeah
    @CrossOrigin(origins = "*")
    @GetMapping("/armyphoto")
    public List<ArmyPhoto> getArmyPhotos() {
        try {
            return ArmyPhotoRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
