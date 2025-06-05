package com.example.restservice.controller;

import com.example.restservice.model.MainTroopType;
import com.example.restservice.repository.MainTroopTypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MainTroopTypeController {
    @GetMapping("/maintrooptype")
    public List<MainTroopType> getMainTroopsType() {
        try {
            return MainTroopTypeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
