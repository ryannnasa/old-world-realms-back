package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class BattleReportPhotoController {
    @CrossOrigin(origins = "*")
    @GetMapping("/battlereportphoto")
    public List<BattleReportPhoto> getArmies() {
        try {
            return BattleReportPhotoRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
