package com.example.restservice.controller;

import com.example.restservice.model.UnitDescription;
import com.example.restservice.repository.UnitDescriptionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UnitDescriptionController {

    @CrossOrigin(origins = "*")
    @GetMapping("/unitdescription")
    public List<UnitDescription> getUnitsDescription() {
        try {
            return UnitDescriptionRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
