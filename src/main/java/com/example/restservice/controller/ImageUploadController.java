package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageUploadController {

    private final Dotenv dotenv;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName = "old-world-realms";

    public ImageUploadController() {
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

        this.s3Presigner = S3Presigner.builder()
                .region(Region.of("fr-par"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://s3.fr-par.scw.cloud"))
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("battleReportId") int battleReportId) {
        try {
            String uuidFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            String key = "battle-report-photos/" + uuidFileName; // ← Dossier ajouté ici

            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            BattleReportPhoto photo = new BattleReportPhoto();
            photo.setNameBattleReportPhoto(uuidFileName); // ← on enregistre juste le nom du fichier (pas le chemin complet)
            photo.setBattleReport_idBattleReport(battleReportId);

            int generatedId = BattleReportPhotoRepository.create(photo);
            photo.setIdBattleReportPhoto(generatedId);

            return ResponseEntity.ok(uuidFileName); // ← toujours le nom sans le chemin
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) {
        try {
            String key = "battle-report-photos/" + filename;

            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            ResponseBytes<GetObjectResponse> response = s3Client.getObjectAsBytes(getRequest);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // à adapter dynamiquement si besoin
                    .body(response.asByteArray());

        } catch (S3Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteImage(@PathVariable String filename) {
        try {
            String key = "battle-report-photos/" + filename;

            DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.deleteObject(deleteRequest);

            return ResponseEntity.ok("Image supprimée : " + filename);
        } catch (S3Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression : " + e.awsErrorDetails().errorMessage());
        }
    }


    @GetMapping("/battlereport/{id}/photos")
    public ResponseEntity<List<BattleReportPhoto>> getPhotosForBattleReport(@PathVariable int id) {
        try {
            List<BattleReportPhoto> photos = BattleReportPhotoRepository.findByBattleReportId(id);
            return ResponseEntity.ok(photos);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/image-url/{filename}")
    public ResponseEntity<String> getPresignedUrl(@PathVariable String filename) {
        try {
            String key = "battle-report-photos/" + filename;
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(r -> r
                    .signatureDuration(Duration.ofMinutes(15))
                    .getObjectRequest(getObjectRequest)
            );
            String presignedUrl = presignedRequest.url().toString();
            return ResponseEntity.ok(presignedUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
    }
}
