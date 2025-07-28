package com.example.restservice.controller;

import com.example.restservice.model.BattleReport;
import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportRepository;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.repository.PlayerRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class BattleReportController {

    private final Dotenv dotenv;
    private final S3Client s3Client;
    private final String bucketName = "old-world-realms";

    public BattleReportController() {
        dotenv = Dotenv.load();
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                dotenv.get("ACCESS_KEY"),
                dotenv.get("SECRET_KEY")
        );

        this.s3Client = S3Client.builder()
                .region(Region.of("fr-par"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://s3.fr-par.scw.cloud"))
                .build();
    }

    // ✅ Nouvelle méthode simplifiée et corrigée d'upload de fichiers
    @PostMapping("/battlereport/{id}/photos")
    public ResponseEntity<List<String>> uploadBattleReportPhotos(
            @PathVariable("id") int battleReportId,
            @RequestParam("fileBattleReportPhoto") MultipartFile[] files
    ) {
        System.out.println("Nb fichiers reçus : " + (files == null ? "null" : files.length));
        if (files != null) {
            for (MultipartFile file : files) {
                System.out.println("Nom du fichier : " + file.getOriginalFilename());
            }
        }
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(List.of("Aucun fichier reçu."));
        }

        try {
            List<String> uploadedNames = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

                // Construction de la requête S3
                PutObjectRequest putRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key("battle-report-photos/" + fileName)
                        .contentType(file.getContentType())
                        .build();

                // Envoi du fichier à S3 - on utilise le nom complet ici pour éviter les conflits d'import
                s3Client.putObject(
                        putRequest,
                        software.amazon.awssdk.core.sync.RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                );

                // Enregistrement en base
                BattleReportPhoto photo = new BattleReportPhoto();
                photo.setNameBattleReportPhoto(fileName);
                photo.setBattleReport_idBattleReport(battleReportId);
                BattleReportPhotoRepository.create(photo);

                uploadedNames.add(fileName);
            }
            return ResponseEntity.ok(uploadedNames);

        } catch (IOException | SQLException e) {
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

    @GetMapping("/battlereport/user/{id}")
    public List<BattleReport> getBattleReportByUserId(@PathVariable("id") String idUser) {
        try {
            return BattleReportRepository.findByUserId(idUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @PostMapping("/battlereport")
    public BattleReport createBattleReport(@RequestBody BattleReport battleReport) {
        try {
            int idBattleReport = BattleReportRepository.create(battleReport);

            if (battleReport.getPhotoFileNames() != null) {
                for (String fileName : battleReport.getPhotoFileNames()) {
                    BattleReportPhotoRepository.create(new BattleReportPhoto(
                            0,
                            fileName,
                            idBattleReport
                    ));
                }
            }

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
            // 1. Supprimer les photos associées
            List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(id);

            Dotenv dotenv = Dotenv.load();
            AwsBasicCredentials credentials = AwsBasicCredentials.create(
                    dotenv.get("ACCESS_KEY"),
                    dotenv.get("SECRET_KEY")
            );

            S3Client s3Client = S3Client.builder()
                    .region(Region.of("fr-par"))
                    .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .endpointOverride(URI.create("https://s3.fr-par.scw.cloud"))
                    .build();

            String bucketName = "old-world-realms";

            for (BattleReportPhoto photo : photos) {
                String key = "battle-report-photos/" + photo.getNameBattleReportPhoto();

                // Supprimer l'image dans S3
                DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();

                s3Client.deleteObject(deleteRequest);

                // Supprimer l'enregistrement en base
                BattleReportPhotoRepository.delete(photo.getIdBattleReportPhoto());
            }

            // 2. Supprimer les joueurs associés
            PlayerRepository.deleteByBattleReportId(id);

            // 3. Supprimer le rapport de bataille
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
            for (String fileName : photoFileNamesToDelete) {
                String key = "battle-report-photos/" + fileName;

                // Supprimer l'image de S3
                DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                s3Client.deleteObject(deleteRequest);

                // Supprimer l'entrée correspondante dans la base
                BattleReportPhoto photo = BattleReportPhotoRepository.findByFileNameAndBattleReportId(fileName, battleReportId);
                if (photo != null) {
                    BattleReportPhotoRepository.delete(photo.getIdBattleReportPhoto());
                }
            }

            return ResponseEntity.ok("Photos supprimées avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression des photos : " + e.getMessage());
        }
    }
}
