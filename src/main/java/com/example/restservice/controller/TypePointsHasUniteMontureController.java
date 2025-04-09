package com.example.restservice.controller;

import com.example.restservice.model.TypePointsHasUniteMonture;
import com.example.restservice.repository.TypePointsHasUniteMontureRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TypePointsHasUniteMontureController {

    @CrossOrigin(origins = "*")
    @GetMapping("/typepointshasunitemonture")
    public List<TypePointsHasUniteMonture> getTypePointsHasUniteMontures() {
        try {
            return TypePointsHasUniteMontureRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
