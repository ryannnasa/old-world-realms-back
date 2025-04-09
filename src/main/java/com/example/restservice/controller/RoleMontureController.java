package com.example.restservice.controller;

import com.example.restservice.model.RoleMonture;
import com.example.restservice.repository.RoleMontureRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class RoleMontureController {

    @CrossOrigin(origins = "*")
    @GetMapping("/rolemonture")
    public List<RoleMonture> getRoleMontures() {
        try {
            return RoleMontureRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
