package com.example.restservice.controller;

import com.example.restservice.model.PointsType;
import com.example.restservice.repository.PointsTypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PointsTypeController {
    @GetMapping("/pointstype")
    public List<PointsType> getPointsTypes() {
        try {
            return PointsTypeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
