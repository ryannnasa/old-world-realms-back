package com.example.restservice.controller;

import com.example.restservice.model.CompositionArmee;
import com.example.restservice.repository.CompositionArmeeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class CompositionArmeeController {

    @CrossOrigin(origins = "*")
    @GetMapping("/compositionarmee")
    public List<CompositionArmee> getCompositionArmees() {
        try {
            return CompositionArmeeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
