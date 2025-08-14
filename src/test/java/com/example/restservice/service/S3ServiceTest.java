package com.example.restservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3ServiceTest {

    @Mock
    private S3Client s3Client;

    @Mock
    private S3Presigner s3Presigner;

    @Mock
    private Dotenv dotenv;

    private S3Service s3Service;

    @BeforeEach
    void setUp() {
        try (MockedStatic<Dotenv> dotenvMockedStatic = mockStatic(Dotenv.class)) {
            dotenvMockedStatic.when(Dotenv::load).thenReturn(dotenv);
            when(dotenv.get("ACCESS_KEY")).thenReturn("test-access-key");
            when(dotenv.get("SECRET_KEY")).thenReturn("test-secret-key");

            s3Service = new S3Service();

            try {
                var s3ClientField = S3Service.class.getDeclaredField("s3Client");
                s3ClientField.setAccessible(true);
                s3ClientField.set(s3Service, s3Client);

                var s3PresignerField = S3Service.class.getDeclaredField("s3Presigner");
                s3PresignerField.setAccessible(true);
                s3PresignerField.set(s3Service, s3Presigner);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void uploadFile_ShouldCallS3ClientPutObject() {
        String fileName = "test-image.jpg";
        InputStream inputStream = new ByteArrayInputStream("test content".getBytes());
        long size = 12L;
        String contentType = "image/jpeg";

        PutObjectResponse response = PutObjectResponse.builder().build();
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
                .thenReturn(response);

        s3Service.uploadFile(fileName, inputStream, size, contentType);

        verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    void downloadFile_ShouldReturnResponseBytes() {
        String fileName = "test-image.jpg";
        ResponseBytes<GetObjectResponse> expectedResponse = mock(ResponseBytes.class);

        when(s3Client.getObjectAsBytes(any(GetObjectRequest.class)))
                .thenReturn(expectedResponse);

        ResponseBytes<GetObjectResponse> result = s3Service.downloadFile(fileName);

        assertEquals(expectedResponse, result);
        verify(s3Client).getObjectAsBytes(any(GetObjectRequest.class));
    }

    @Test
    void deleteFile_ShouldCallS3ClientDeleteObject() {
        String fileName = "test-image.jpg";
        DeleteObjectResponse response = DeleteObjectResponse.builder().build();

        when(s3Client.deleteObject(any(DeleteObjectRequest.class)))
                .thenReturn(response);

        s3Service.deleteFile(fileName);

        verify(s3Client).deleteObject(any(DeleteObjectRequest.class));
    }

    @Test
    void getPresignedUrl_WithDuration_ShouldReturnUrl() throws Exception {
        String fileName = "test-image.jpg";
        Duration duration = Duration.ofMinutes(30);
        String expectedUrl = "https://presigned-url.example.com";

        PresignedGetObjectRequest presignedRequest = mock(PresignedGetObjectRequest.class);
        when(presignedRequest.url()).thenReturn(new URL(expectedUrl));
        when(s3Presigner.presignGetObject(any(java.util.function.Consumer.class))).thenReturn(presignedRequest);

        String result = s3Service.getPresignedUrl(fileName, duration);

        assertEquals(expectedUrl, result);
        verify(s3Presigner).presignGetObject(any(java.util.function.Consumer.class));
    }

    @Test
    void getPresignedUrl_WithoutDuration_ShouldUseDefault15Minutes() throws Exception {
        String fileName = "test-image.jpg";
        String expectedUrl = "https://presigned-url.example.com";

        PresignedGetObjectRequest presignedRequest = mock(PresignedGetObjectRequest.class);
        when(presignedRequest.url()).thenReturn(new URL(expectedUrl));
        when(s3Presigner.presignGetObject(any(java.util.function.Consumer.class))).thenReturn(presignedRequest);

        String result = s3Service.getPresignedUrl(fileName);

        assertEquals(expectedUrl, result);
        verify(s3Presigner).presignGetObject(any(java.util.function.Consumer.class));
    }

    @Test
    void uploadFile_WhenS3ThrowsException_ShouldPropagateException() {
        String fileName = "test-image.jpg";
        InputStream inputStream = new ByteArrayInputStream("test content".getBytes());
        long size = 12L;
        String contentType = "image/jpeg";

        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
                .thenThrow(S3Exception.builder().message("S3 Error").build());

        assertThrows(S3Exception.class, () ->
            s3Service.uploadFile(fileName, inputStream, size, contentType)
        );
    }
}
