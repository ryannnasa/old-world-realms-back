package com.example.restservice.controller;

import com.example.restservice.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitoring/logs")
public class LogsController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping("/recent")
    public ResponseEntity<List<Map<String, Object>>> getRecentLogs() {
        return ResponseEntity.ok(loggingService.getRecentEvents());
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Map<String, Object>>> getLogsByLevel(@PathVariable String level) {
        return ResponseEntity.ok(loggingService.getRecentEventsByLevel(level.toUpperCase()));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Map<String, Object>>> getLogsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(loggingService.getRecentEventsByCategory(category.toUpperCase()));
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getLoggingStatistics() {
        return ResponseEntity.ok(loggingService.getLoggingStatistics());
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> getLogFiles() {
        return ResponseEntity.ok(loggingService.getAvailableLogFiles());
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> testLogging() {
        loggingService.logInfo("TEST", "Test de logging depuis l'API");
        loggingService.logWarning("TEST", "Test d'alerte depuis l'API");
        return ResponseEntity.ok(Map.of("message", "Tests de logging effectués"));
    }
}
