package com.example.restservice.controller;

import com.example.restservice.model.Scenario;
import com.example.restservice.repository.ScenarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ScenarioController {
    @GetMapping("/scenario")
    public List<Scenario> getScenarios() {
        try {
            return ScenarioRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
