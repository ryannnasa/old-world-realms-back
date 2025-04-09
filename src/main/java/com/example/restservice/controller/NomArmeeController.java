package com.example.restservice.controller;

import com.example.restservice.model.NomArmee;
import com.example.restservice.repository.NomArmeeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class NomArmeeController {

    @CrossOrigin(origins = "*")
    @GetMapping("/nomarmee")
    public List<NomArmee> getNomArmees() {
        try {
            return NomArmeeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
