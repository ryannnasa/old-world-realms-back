package com.example.restservice.controller;

import com.example.restservice.model.TypePoints;
import com.example.restservice.repository.TypePointsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TypePointsController {

    @CrossOrigin(origins = "*")
    @GetMapping("/typepoints")
    public List<TypePoints> getTypePoints() {
        try {
            return TypePointsRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
