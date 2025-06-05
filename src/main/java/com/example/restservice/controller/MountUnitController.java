package com.example.restservice.controller;

import com.example.restservice.model.MountUnit;
import com.example.restservice.repository.MountUnitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MountUnitController {
    @GetMapping("/mountunit")
    public List<MountUnit> getMountsUnit() {
        try {
            return MountUnitRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
