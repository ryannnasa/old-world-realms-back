package com.example.restservice.controller;

import com.example.restservice.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        Map<String, Object> health = monitoringService.getApplicationHealth();
        boolean isHealthy = "UP".equals(health.get("status"));
        return ResponseEntity.status(isHealthy ? 200 : 503).body(health);
    }

    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> getMetrics() {
        return ResponseEntity.ok(monitoringService.getSystemMetrics());
    }

    @GetMapping("/database")
    public ResponseEntity<Map<String, Object>> getDatabaseHealth() {
        Map<String, Object> dbHealth = monitoringService.getDatabaseHealth();
        boolean isHealthy = "UP".equals(dbHealth.get("status"));
        return ResponseEntity.status(isHealthy ? 200 : 503).body(dbHealth);
    }

    @GetMapping("/s3")
    public ResponseEntity<Map<String, Object>> getS3Health() {
        Map<String, Object> s3Health = monitoringService.getS3Health();
        boolean isHealthy = "UP".equals(s3Health.get("status"));
        return ResponseEntity.status(isHealthy ? 200 : 503).body(s3Health);
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getComprehensiveReport() {
        return ResponseEntity.ok(monitoringService.getComprehensiveReport());
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getSimpleStatus() {
        Map<String, Object> health = monitoringService.getApplicationHealth();
        String status = (String) health.get("status");
        return ResponseEntity.ok(Map.of(
            "status", status,
            "timestamp", (String) health.get("timestamp")
        ));
    }
}
