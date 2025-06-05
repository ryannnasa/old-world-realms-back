package com.example.restservice.controller;

import com.example.restservice.model.PointsCases;
import com.example.restservice.repository.PointsCasesRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PointsCasesController {
    @GetMapping("/pointscases")
    public List<PointsCases> getPointsCases() {
        try {
            return PointsCasesRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
