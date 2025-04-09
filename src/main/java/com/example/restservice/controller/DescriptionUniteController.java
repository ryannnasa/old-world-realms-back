package com.example.restservice.controller;

import com.example.restservice.model.DescriptionUnite;
import com.example.restservice.repository.DescriptionUniteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class DescriptionUniteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/descriptionunite")
    public List<DescriptionUnite> getDescriptionUnites() {
        try {
            return DescriptionUniteRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
