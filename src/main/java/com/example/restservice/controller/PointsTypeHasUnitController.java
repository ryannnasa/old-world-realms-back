package com.example.restservice.controller;

import com.example.restservice.model.PointsTypeHasUnit;
import com.example.restservice.repository.PointsTypeHasUnitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PointsTypeHasUnitController {
    @GetMapping("/pointstypehasunit")
    public List<PointsTypeHasUnit> getPointsTypeHasUnits() {
        try {
            return PointsTypeHasUnitRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
