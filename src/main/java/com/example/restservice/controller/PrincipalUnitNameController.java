package com.example.restservice.controller;

import com.example.restservice.model.PrincipalUnitName;
import com.example.restservice.repository.PrincipalUnitNameRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class PrincipalUnitNameController {
    @GetMapping("/principalunitname")
    public List<PrincipalUnitName> getPrincipalUnitsName() {
        try {
            return PrincipalUnitNameRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
