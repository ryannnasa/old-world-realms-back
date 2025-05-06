package com.example.restservice.controller;

import com.example.restservice.model.AttachedUnit;
import com.example.restservice.repository.AttachedUnitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class AttachedUnitController {

    @CrossOrigin(origins = "*")
    @GetMapping("/attachedunit")
    public List<AttachedUnit> getAttachedUnits() {
        try {
            return AttachedUnitRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
