package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageUploadControllerTest {

    @Mock
    private S3Service s3Service;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private ImageUploadController imageUploadController;

    @Test
    void testUploadImageSuccess() throws IOException, SQLException {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.create(any(BattleReportPhoto.class))).thenReturn(1);

            ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
            assertTrue(result.getBody().contains("test.jpg"));
        }
    }

    @Test
    void testUploadImageIOException() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenThrow(new IOException("IO Error"));

        ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Upload failed", result.getBody());
    }

    @Test
    void testUploadImageSQLException() throws IOException, SQLException {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.create(any(BattleReportPhoto.class)))
                    .thenThrow(new SQLException("Database error"));

            ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
            assertEquals("Upload failed", result.getBody());
        }
    }

    @Test
    void testDownloadImageSuccess() {
        ResponseBytes<GetObjectResponse> mockResponse = mock(ResponseBytes.class);
        byte[] imageData = "image data".getBytes();
        when(mockResponse.asByteArray()).thenReturn(imageData);
        when(s3Service.downloadFile("test.jpg")).thenReturn(mockResponse);

        ResponseEntity<byte[]> result = imageUploadController.downloadImage("test.jpg");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertArrayEquals(imageData, result.getBody());
    }

    @Test
    void testDownloadImageNotFound() {
        when(s3Service.downloadFile("nonexistent.jpg")).thenThrow(S3Exception.builder().build());

        ResponseEntity<byte[]> result = imageUploadController.downloadImage("nonexistent.jpg");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void testDeleteImageSuccess() {
        doNothing().when(s3Service).deleteFile("test.jpg");

        ResponseEntity<String> result = imageUploadController.deleteImage("test.jpg");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Image supprimée : test.jpg", result.getBody());
    }

    @Test
    void testDeleteImageError() {
        AwsErrorDetails errorDetails = AwsErrorDetails.builder()
                .errorMessage("File not found")
                .build();
        S3Exception s3Exception = (S3Exception) S3Exception.builder()
                .awsErrorDetails(errorDetails)
                .build();

        doThrow(s3Exception).when(s3Service).deleteFile("test.jpg");

        ResponseEntity<String> result = imageUploadController.deleteImage("test.jpg");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(result.getBody().contains("Erreur lors de la suppression"));
    }

    @Test
    void testGetPhotosForBattleReportSuccess() throws SQLException {
        List<BattleReportPhoto> photos = Arrays.asList(new BattleReportPhoto());

        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.findByBattleReportId(1)).thenReturn(photos);

            ResponseEntity<List<BattleReportPhoto>> result = imageUploadController.getPhotosForBattleReport(1);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(photos, result.getBody());
        }
    }

    @Test
    void testGetPhotosForBattleReportError() throws SQLException {
        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.findByBattleReportId(1))
                    .thenThrow(new SQLException("Database error"));

            ResponseEntity<List<BattleReportPhoto>> result = imageUploadController.getPhotosForBattleReport(1);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
            assertNull(result.getBody());
        }
    }

    @Test
    void testGetPresignedUrlSuccess() {
        String presignedUrl = "https://example.com/presigned-url";
        when(s3Service.getPresignedUrl("test.jpg")).thenReturn(presignedUrl);

        ResponseEntity<String> result = imageUploadController.getPresignedUrl("test.jpg");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(presignedUrl, result.getBody());
    }

    @Test
    void testGetPresignedUrlError() {
        when(s3Service.getPresignedUrl("test.jpg")).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<String> result = imageUploadController.getPresignedUrl("test.jpg");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(result.getBody().contains("Erreur"));
    }
}
