package com.example.restservice.controller;

import com.example.restservice.model.TroopType;
import com.example.restservice.repository.TroopTypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class TroopTypeController {
    @GetMapping("/trooptype")
    public List<TroopType> getTroopsType() {
        try {
            return TroopTypeRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
