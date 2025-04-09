package com.example.restservice.controller;

import com.example.restservice.model.TypeTroupe;
import com.example.restservice.repository.TypeTroupeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TypeTroupeController {

    @CrossOrigin(origins = "*")
    @GetMapping("/typetroupe")
    public List<TypeTroupe> getTypeTroupes() {
        try {
            return TypeTroupeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
