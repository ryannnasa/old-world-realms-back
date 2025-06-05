package com.example.restservice.controller;

import com.example.restservice.model.PointsTypeHasMountUnit;
import com.example.restservice.repository.PointsTypeHasMountUnitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PointsTypeHasMountUnitController {
    @GetMapping("/pointstypehasmountunit")
    public List<PointsTypeHasMountUnit> getPointsTypeHasMountUnits() {
        try {
            return PointsTypeHasMountUnitRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
