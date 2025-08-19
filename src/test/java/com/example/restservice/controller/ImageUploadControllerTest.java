package com.example.restservice.controller;

import com.example.restservice.model.BattleReportPhoto;
import com.example.restservice.repository.BattleReportPhotoRepository;
import com.example.restservice.service.S3Service;
import com.example.restservice.service.SecurityMonitoringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageUploadControllerTest {

    @Mock
    private S3Service s3Service;

    @Mock
    private SecurityMonitoringService securityService;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private ImageUploadController imageUploadController;

    private MockHttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.setRemoteAddr("127.0.0.1");

        // Configuration des mocks pour éviter les NullPointerException
        doNothing().when(securityService).recordSuspiciousActivity(anyString(), anyString());
        doNothing().when(securityService).logSecurityEvent(anyString(), anyString(), anyString());
        doNothing().when(securityService).validateInput(anyString(), anyString());
    }

    @Test
    void testUploadImageSuccess() throws IOException, SQLException {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.create(any(BattleReportPhoto.class))).thenReturn(1);

            ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
            assertTrue(result.getBody().contains("test.jpg"));
        }
    }

    @Test
    void testUploadImageIOException() throws IOException {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenThrow(new IOException("IO Error"));
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Upload failed", result.getBody());
    }

    @Test
    void testUploadImageSQLException() throws SQLException, IOException {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        try (MockedStatic<BattleReportPhotoRepository> mockedRepo = mockStatic(BattleReportPhotoRepository.class)) {
            mockedRepo.when(() -> BattleReportPhotoRepository.create(any(BattleReportPhoto.class))).thenThrow(new SQLException("SQL Error"));

            ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
            assertEquals("Upload failed", result.getBody());
        }
    }

    @Test
    void testDownloadImageSuccess() {
        ResponseBytes<GetObjectResponse> mockResponse = mock(ResponseBytes.class);
        GetObjectResponse getObjectResponse = mock(GetObjectResponse.class);

        when(mockResponse.response()).thenReturn(getObjectResponse);
        when(getObjectResponse.contentType()).thenReturn("image/jpeg");
        when(mockResponse.asByteArray()).thenReturn("test".getBytes());
        when(s3Service.downloadFile("test.jpg")).thenReturn(mockResponse);

        ResponseEntity<byte[]> result = imageUploadController.downloadImage("test.jpg", mockRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertArrayEquals("test".getBytes(), result.getBody());
    }

    @Test
    void testDownloadImageNotFound() {
        when(s3Service.downloadFile("nonexistent.jpg")).thenThrow(S3Exception.builder().build());

        ResponseEntity<byte[]> result = imageUploadController.downloadImage("nonexistent.jpg", mockRequest);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testUploadImageEmptyFile() {
        when(multipartFile.isEmpty()).thenReturn(true);

        ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Fichier vide", result.getBody());
    }

    @Test
    void testUploadImageTooLarge() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(15L * 1024 * 1024); // 15MB

        ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Fichier trop volumineux", result.getBody());
    }

    @Test
    void testUploadImageInvalidContentType() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(4L);
        when(multipartFile.getContentType()).thenReturn("text/plain");

        ResponseEntity<String> result = imageUploadController.uploadImage(multipartFile, 1, mockRequest);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Type de fichier non autorisé", result.getBody());
    }
}
