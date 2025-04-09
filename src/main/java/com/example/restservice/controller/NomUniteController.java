package com.example.restservice.controller;

import com.example.restservice.model.NomUnite;
import com.example.restservice.repository.NomUniteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class NomUniteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/nomunite")
    public List<NomUnite> getNomUnites() {
        try {
            return NomUniteRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
