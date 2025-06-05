package com.example.restservice.controller;

import com.example.restservice.model.Unit;
import com.example.restservice.repository.UnitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UnitController {
    @GetMapping("/unit")
    public List<Unit> getUnits() {
        try {
            return UnitRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
