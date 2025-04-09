package com.example.restservice.controller;

import com.example.restservice.model.Armee;
import com.example.restservice.repository.ArmeeRepository;  // Assurez-vous d'avoir ce repository pour l'accès à la base de données.
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class ArmeeController {
// Yeah
    @CrossOrigin(origins = "*")
    @GetMapping("/armee")
    public List<Armee> getArmees() {
        try {
            return ArmeeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
