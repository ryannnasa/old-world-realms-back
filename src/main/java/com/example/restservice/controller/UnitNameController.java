package com.example.restservice.controller;

import com.example.restservice.model.UnitName;
import com.example.restservice.repository.UnitNameRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UnitNameController {
    @GetMapping("/unitname")
    public List<UnitName> getUnitsNames() {
        try {
            return UnitNameRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
