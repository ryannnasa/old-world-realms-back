package com.example.restservice.controller;

import com.example.restservice.model.CasPoints;
import com.example.restservice.repository.CasPointsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class CasPointsController {

    @CrossOrigin(origins = "*")
    @GetMapping("/caspoints")
    public List<CasPoints> getCasPoints() {
        try {
            return CasPointsRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
