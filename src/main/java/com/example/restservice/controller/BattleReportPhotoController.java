package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.model.Player;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BattleReportPhotoController {
    @GetMapping("/battlereportphoto")
    public List<BattleReportPhoto> getBattleReportPhotos() {
        try {
            return BattleReportPhotoRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/battlereportphoto/{id}")
    public BattleReportPhoto getBattleReportPhoto(@PathVariable int id) {
        try {
            return BattleReportPhotoRepository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle Report photo not found with the Id : " + id);
        }
    }

    @PostMapping("/battlereportphoto")
    public @ResponseBody Map<String, Object> createBattleReportPhoto(
            @RequestParam("battleReport_idBattleReport") int battleReport_idBattleReport,
            @RequestParam("file") MultipartFile file) {

        Map<String, Object> response = new HashMap<>();
        try {
            BattleReportPhoto battleReportPhoto = new BattleReportPhoto();
            battleReportPhoto.setBattleReport_idBattleReport(battleReport_idBattleReport);
            battleReportPhoto.setFileBattleReportPhoto(file.getBytes()); // conversion du fichier en tableau de bytes

            int generatedId = BattleReportPhotoRepository.create(battleReportPhoto);

            if (generatedId > 0) {
                response.put("message", "Battle Report Photo created successfully.");
                response.put("idBattleReportPhoto", generatedId);
            } else {
                response.put("message", "Failed to create battle report photo.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            response.put("message", "Error creating battle report photo.");
        }
        return response;
    }


    @PutMapping("/battlereportphoto/{id}")
    public @ResponseBody Map<String, String> updateBattleReportPhoto(@PathVariable int id, @RequestBody BattleReportPhoto battleReportPhoto) {
        try {
            int rows = BattleReportPhotoRepository.update(id, battleReportPhoto);
            return Collections.singletonMap("message",
                    rows > 0 ? "Player updated successfully." : "Battle Report Photo not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.singletonMap("message", "Error updating Battle Report Photo.");
        }
    }

    @DeleteMapping("/battlereportphoto/{id}")
    public @ResponseBody Map<String, String> deleteBattleReportPhoto(@PathVariable int id) {
        try {
            int rows = BattleReportPhotoRepository.delete(id);
            return Collections.singletonMap("message",
                    rows > 0 ? "Battle report photo deleted successfully." : "Battle report photo not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.singletonMap("message", "Error deleting battle report photo.");
        }
    }
}