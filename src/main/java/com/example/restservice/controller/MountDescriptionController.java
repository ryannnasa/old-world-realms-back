package com.example.restservice.controller;

import com.example.restservice.model.MountDescription;
import com.example.restservice.repository.MountDescriptionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MountDescriptionController {
    @GetMapping("/mountdescription")
    public List<MountDescription> getMountsDescription() {
        try {
            return MountDescriptionRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
