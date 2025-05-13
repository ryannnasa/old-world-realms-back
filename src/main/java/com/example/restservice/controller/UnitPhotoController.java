package com.example.restservice.controller;

import com.example.restservice.model.UnitPhoto;
import com.example.restservice.repository.UnitPhotoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UnitPhotoController {
    @CrossOrigin(origins = "*")
    @GetMapping("/unitphoto")
    public List<UnitPhoto> getUnitPhotos() {
        try {
            return UnitPhotoRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
