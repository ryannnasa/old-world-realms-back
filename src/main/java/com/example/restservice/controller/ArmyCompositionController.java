package com.example.restservice.controller;

import com.example.restservice.model.ArmyComposition;
import com.example.restservice.repository.ArmyCompositionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ArmyCompositionController {
    @GetMapping("/armycomposition")
    public List<ArmyComposition> getArmiesComposition() {
        try {
            return ArmyCompositionRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
