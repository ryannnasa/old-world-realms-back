package com.example.restservice.controller;

import com.example.restservice.model.Army;
import com.example.restservice.repository.ArmyRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ArmyController {
    @CrossOrigin(origins = "*")
    @GetMapping("/army")
    public List<Army> getArmies() {
        try {
            return ArmyRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
