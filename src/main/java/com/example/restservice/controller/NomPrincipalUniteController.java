package com.example.restservice.controller;

import com.example.restservice.model.NomPrincipalUnite;
import com.example.restservice.repository.NomPrincipalUniteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class NomPrincipalUniteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/nomprincipalunite")
    public List<NomPrincipalUnite> getNomPrincipalUnites() {
        try {
            return NomPrincipalUniteRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
