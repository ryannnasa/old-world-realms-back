package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import com.example.restservice.service.SecurityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageUploadController {

    private final S3Service s3Service;

    @Autowired
    private SecurityMonitoringService securityService;

    public ImageUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("battleReportId") int battleReportId,
            HttpServletRequest request) {

        String clientIp = getClientIp(request);

        try {
            // Validation de sécurité simple
            if (file.isEmpty()) {
                securityService.recordSuspiciousActivity("Empty file upload attempt", clientIp);
                return ResponseEntity.badRequest().body("Fichier vide");
            }

            if (file.getSize() > 10 * 1024 * 1024) { // 10MB
                securityService.recordSuspiciousActivity("Large file upload attempt: " + file.getSize(), clientIp);
                return ResponseEntity.badRequest().body("Fichier trop volumineux");
            }

            // Vérifier le type de fichier
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                securityService.recordSuspiciousActivity("Non-image file upload attempt: " + contentType, clientIp);
                return ResponseEntity.badRequest().body("Type de fichier non autorisé");
            }

            String uuidFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            s3Service.uploadFile(uuidFileName, file.getInputStream(), file.getSize(), file.getContentType());

            BattleReportPhoto photo = new BattleReportPhoto();
            photo.setNameBattleReportPhoto(uuidFileName);
            photo.setBattleReport_idBattleReport(battleReportId);
            photo.setIdBattleReportPhoto(BattleReportPhotoRepository.create(photo));

            securityService.logSecurityEvent("FILE_UPLOAD_SUCCESS", "File: " + uuidFileName, clientIp);
            return ResponseEntity.ok(uuidFileName);

        } catch (IOException | SQLException e) {
            securityService.logSecurityEvent("FILE_UPLOAD_ERROR", e.getMessage(), clientIp);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename, HttpServletRequest request) {
        String clientIp = getClientIp(request);

        try {
            // Validation du nom de fichier
            securityService.validateInput(filename, "filename");

            System.out.println("Attempting to download image: " + filename);
            ResponseBytes<GetObjectResponse> response = s3Service.downloadFile(filename);

            // Déterminer le type de contenu
            String contentType = response.response().contentType();
            if (contentType == null || contentType.isEmpty()) {
                if (filename.toLowerCase().endsWith(".png")) {
                    contentType = "image/png";
                } else if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
                    contentType = "image/jpeg";
                } else if (filename.toLowerCase().endsWith(".webp")) {
                    contentType = "image/webp";
                } else {
                    contentType = "image/jpeg";
                }
            }

            System.out.println("Successfully downloaded image: " + filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header("Cache-Control", "max-age=3600")
                    .body(response.asByteArray());

        } catch (SecurityException e) {
            securityService.recordSuspiciousActivity("Malicious filename: " + filename, clientIp);
            return ResponseEntity.badRequest().body(null);
        } catch (S3Exception e) {
            System.err.println("Error downloading image " + filename + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            securityService.logSecurityEvent("FILE_DOWNLOAD_ERROR", e.getMessage(), clientIp);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

    @GetMapping("/s3/test")
    public ResponseEntity<String> testS3Connection() {
        try {
            boolean isConnected = s3Service.testConnection();
            return ResponseEntity.ok("S3 Connection: " + (isConnected ? "SUCCESS" : "FAILED"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("S3 Connection FAILED: " + e.getMessage());
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return xForwardedForHeader.split(",")[0];
        }
    }
}
