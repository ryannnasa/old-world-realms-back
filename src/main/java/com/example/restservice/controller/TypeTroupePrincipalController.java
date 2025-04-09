package com.example.restservice.controller;

import com.example.restservice.model.TypeTroupePrincipal;
import com.example.restservice.repository.TypeTroupePrincipalRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TypeTroupePrincipalController {

    @CrossOrigin(origins = "*")
    @GetMapping("/typetroupeprincipal")
    public List<TypeTroupePrincipal> getTypeTroupePrincipals() {
        try {
            return TypeTroupePrincipalRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
