package com.example.restservice.controller;

import com.example.restservice.model.UniteMonture;
import com.example.restservice.repository.UniteMontureRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UniteMontureController {

    @CrossOrigin(origins = "*")
    @GetMapping("/unitemonture")
    public List<UniteMonture> getUniteMontures() {
        try {
            return UniteMontureRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
