package com.example.restservice.controller;

import com.example.restservice.model.DescriptionMonture;
import com.example.restservice.repository.DescriptionMontureRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class DescriptionMontureController {

    @CrossOrigin(origins = "*")
    @GetMapping("/descriptionmonture")
    public List<DescriptionMonture> getDescriptionMontures() {
        try {
            return DescriptionMontureRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
