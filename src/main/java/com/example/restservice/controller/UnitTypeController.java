package com.example.restservice.controller;

import com.example.restservice.model.UnitType;
import com.example.restservice.repository.UnitTypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UnitTypeController {
    @CrossOrigin(origins = "*")
    @GetMapping("/unittype")
    public List<UnitType> getUnitTypes() {
        try {
            return UnitTypeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
