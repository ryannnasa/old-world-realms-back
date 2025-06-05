package com.example.restservice.controller;

import com.example.restservice.model.MountedUnitPhoto;
import com.example.restservice.repository.MountedUnitPhotoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MountedUnitPhotoController {
    @GetMapping("/mountedunitphoto")
    public List<MountedUnitPhoto> getArmies() {
        try {
            return MountedUnitPhotoRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
