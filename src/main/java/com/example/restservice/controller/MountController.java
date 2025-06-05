package com.example.restservice.controller;

import com.example.restservice.model.Mount;
import com.example.restservice.repository.MountRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MountController {
    @GetMapping("/mount")
    public List<Mount> getMounts() {
        try {
            return MountRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
