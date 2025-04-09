package com.example.restservice.controller;

import com.example.restservice.model.UniteRattachee;
import com.example.restservice.repository.UniteRattacheeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class UniteRattacheeController {

    @CrossOrigin(origins = "*")
    @GetMapping("/uniterattachee")
    public List<UniteRattachee> getUniteRattachees() {
        try {
            return UniteRattacheeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
