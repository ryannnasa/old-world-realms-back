package com.example.restservice.controller;

import com.example.restservice.model.Monture;
import com.example.restservice.repository.MontureRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MontureController {

    @CrossOrigin(origins = "*")
    @GetMapping("/monture")
    public List<Monture> getMontures() {
        try {
            return MontureRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
