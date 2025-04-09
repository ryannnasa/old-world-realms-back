package com.example.restservice.controller;

import com.example.restservice.model.TypePointsHasUnite;
import com.example.restservice.repository.TypePointsHasUniteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TypePointsHasUniteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/typepointshasunite")
    public List<TypePointsHasUnite> getTypePointsHasUnites() {
        try {
            return TypePointsHasUniteRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
