package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.repository.BattleReportRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class BattleReportController {
    @CrossOrigin(origins = "*")
    @GetMapping("/battlereport")
    public List<BattleReport> getBattleReports() {
        try {
            return BattleReportRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
