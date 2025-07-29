package com.example.restservice.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.InputStream;
import java.net.URI;
import java.time.Duration;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName = "old-world-realms";
    private static final String PHOTO_PREFIX = "battle-report-photos/";

    public S3Service() {
        Dotenv dotenv = Dotenv.load();
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

    public void uploadFile(String fileName, InputStream inputStream, long size, String contentType) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(PHOTO_PREFIX + fileName)
                        .contentType(contentType)
                        .build(),
                RequestBody.fromInputStream(inputStream, size)
        );
    }

    public ResponseBytes<GetObjectResponse> downloadFile(String fileName) {
        return s3Client.getObjectAsBytes(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(PHOTO_PREFIX + fileName)
                        .build()
        );
    }

    public void deleteFile(String fileName) {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(PHOTO_PREFIX + fileName)
                        .build()
        );
    }

    public String getPresignedUrl(String fileName, Duration duration) {
        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(r -> r
                .signatureDuration(duration)
                .getObjectRequest(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(PHOTO_PREFIX + fileName)
                        .build())
        );
        return presignedRequest.url().toString();
    }

    public String getPresignedUrl(String fileName) {
        return getPresignedUrl(fileName, Duration.ofMinutes(15));
    }
}
