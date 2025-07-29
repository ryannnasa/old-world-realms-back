package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageUploadController {

    private final S3Service s3Service;

    public ImageUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("battleReportId") int battleReportId) {
        try {
            String uuidFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            s3Service.uploadFile(uuidFileName, file.getInputStream(), file.getSize(), file.getContentType());

            BattleReportPhoto photo = new BattleReportPhoto();
            photo.setNameBattleReportPhoto(uuidFileName);
            photo.setBattleReport_idBattleReport(battleReportId);
            photo.setIdBattleReportPhoto(BattleReportPhotoRepository.create(photo));

            return ResponseEntity.ok(uuidFileName);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) {
        try {
            ResponseBytes<GetObjectResponse> response = s3Service.downloadFile(filename);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(response.asByteArray());

        } catch (S3Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteImage(@PathVariable String filename) {
        try {
            s3Service.deleteFile(filename);
            return ResponseEntity.ok("Image supprimée : " + filename);
        } catch (S3Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression : " + e.awsErrorDetails().errorMessage());
        }
    }

    @GetMapping("/battlereport/{id}/photos")
    public ResponseEntity<List<BattleReportPhoto>> getPhotosForBattleReport(@PathVariable int id) {
        try {
            return ResponseEntity.ok(BattleReportPhotoRepository.findByBattleReportId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/image-url/{filename}")
    public ResponseEntity<String> getPresignedUrl(@PathVariable String filename) {
        try {
            String presignedUrl = s3Service.getPresignedUrl(filename);
            return ResponseEntity.ok(presignedUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
    }
}
