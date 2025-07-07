package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.repository.BattleReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class BattleReportController {
    @GetMapping("/battlereport")
    public List<BattleReport> getBattleReports() {
        try {
            return BattleReportRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/battlereport/{id}")
    public BattleReport getBattleReport(@PathVariable int id) {
        try {
            return BattleReportRepository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle Report not found with the Id : " + id);
        }
    }

    @PostMapping("/battlereport")

    public BattleReport createBattleReport(@RequestBody BattleReport battleReport) {
        try {
            int idBattleReport = BattleReportRepository.create(battleReport);
            // Récupérer le BattleReport complet avec joueurs chargés
            return BattleReportRepository.findById(idBattleReport);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating battle report.");
        }
    }


    @PutMapping("/battlereport/{id}")
    public String updateBattleReport(@PathVariable int id, @RequestBody BattleReport battleReport) {
        try {
            int rows = BattleReportRepository.update(id, battleReport);
            return rows > 0 ? "Battle report updated successfully." : "Battle report not found.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating battle report.";
        }
    }

    @DeleteMapping("/battlereport/{id}")
    public String deleteBattleReport(@PathVariable int id) {
        try {
            int rows = BattleReportRepository.delete(id);
            return rows > 0 ? "Battle report deleted successfully." : "Battle report not found.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting battle report.";
        }
    }
}
