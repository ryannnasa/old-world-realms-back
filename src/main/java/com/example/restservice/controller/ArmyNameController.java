package com.example.restservice.controller;

import com.example.restservice.model.ArmyName;
import com.example.restservice.repository.ArmyNameRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ArmyNameController {

    @CrossOrigin(origins = "*")
    @GetMapping("/armyname")
    public List<ArmyName> getArmiesName() {
        try {
            return ArmyNameRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
