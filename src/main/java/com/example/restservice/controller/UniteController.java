package com.example.restservice.controller;

import com.example.restservice.model.Unite;
import com.example.restservice.repository.UniteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UniteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/unite")
    public List<Unite> getUnites() {
        try {
            return UniteRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
