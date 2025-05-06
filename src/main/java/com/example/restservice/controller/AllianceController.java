package com.example.restservice.controller;

import com.example.restservice.model.Alliance;
import com.example.restservice.repository.AllianceRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class AllianceController {
    @CrossOrigin(origins = "*")
    @GetMapping("/alliance")
    public List<Alliance> getAlliances() {
        try {
            return AllianceRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
