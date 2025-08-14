package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportRepository;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"}, allowCredentials = "true")
public class BattleReportController {

    private final S3Service s3Service;

    public BattleReportController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/battlereport/{id}/photos")
    public ResponseEntity<List<String>> uploadBattleReportPhotos(
            @PathVariable("id") int battleReportId,
            @RequestParam("fileBattleReportPhoto") MultipartFile[] files
    ) {
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(List.of("Aucun fichier reçu."));
        }

        try {
            List<String> uploadedNames = Arrays.stream(files)
                    .filter(file -> !file.isEmpty())
                    .map(file -> {
                        try {
                            String fileName = UUID.randomUUID().toString();

                            s3Service.uploadFile(fileName, file.getInputStream(), file.getSize(), file.getContentType());

                            BattleReportPhoto photo = new BattleReportPhoto();
                            photo.setNameBattleReportPhoto(fileName);
                            photo.setBattleReport_idBattleReport(battleReportId);
                            BattleReportPhotoRepository.create(photo);

                            return fileName;
                        } catch (IOException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(uploadedNames);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Upload failed"));
        }
    }

    @GetMapping("/battlereport")
    public List<BattleReport> getBattleReports() {
        try {
            return BattleReportRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
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

    @GetMapping("/battlereport/user/{id}")
    public List<BattleReport> getBattleReportByUserId(@PathVariable("id") String idUser) {
        try {
            return BattleReportRepository.findByUserId(idUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @PostMapping("/battlereport")
    public BattleReport createBattleReport(@RequestBody BattleReport battleReport) {
        try {
            int idBattleReport = BattleReportRepository.create(battleReport);
            return BattleReportRepository.findById(idBattleReport);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating battle report.");
        }
    }

    @PutMapping("/battlereport/{id}")
    public BattleReport updateBattleReport(@PathVariable int id, @RequestBody BattleReport battleReport) {
        try {
            int rows = BattleReportRepository.update(id, battleReport);
            if (rows > 0) {
                return BattleReportRepository.findById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle report not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating battle report.");
        }
    }

    @DeleteMapping("/battlereport/{id}")
    public ResponseEntity<String> deleteBattleReport(@PathVariable int id) {
        try {
            BattleReportPhotoRepository.findByBattleReportId(id)
                    .forEach(photo -> {
                        try {
                            s3Service.deleteFile(photo.getNameBattleReportPhoto());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

            BattleReportRepository.delete(id);
            return ResponseEntity.ok("Rapport de bataille et ses images supprimés avec succès.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    @DeleteMapping("/battlereport/{id}/photos")
    public ResponseEntity<String> deleteBattleReportPhotos(
            @PathVariable("id") int battleReportId,
            @RequestBody List<String> photoFileNamesToDelete
    ) {
        if (photoFileNamesToDelete == null || photoFileNamesToDelete.isEmpty()) {
            return ResponseEntity.badRequest().body("Aucun nom de fichier à supprimer.");
        }

        try {
            photoFileNamesToDelete.forEach(fileName -> {
                try {
                    s3Service.deleteFile(fileName);

                    BattleReportPhoto photoToDelete = BattleReportPhotoRepository
                            .findByFileNameAndBattleReportId(fileName, battleReportId);
                    if (photoToDelete != null) {
                        BattleReportPhotoRepository.delete(photoToDelete.getIdBattleReportPhoto());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Erreur lors de la suppression de " + fileName, e);
                }
            });

            return ResponseEntity.ok("Photos supprimées avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression : " + e.getMessage());
        }
    }
}
