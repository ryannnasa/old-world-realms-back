package com.example.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restservice.DatabaseSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringService {

    @Autowired
    private S3Service s3Service;

    private final Runtime runtime = Runtime.getRuntime();
    private final long startTime = System.currentTimeMillis();

    public Map<String, Object> getSystemMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        metrics.put("memory", Map.of(
            "total", formatBytes(totalMemory),
            "used", formatBytes(usedMemory),
            "free", formatBytes(freeMemory),
            "max", formatBytes(maxMemory),
            "usage_percentage", Math.round((double) usedMemory / totalMemory * 100)
        ));

        metrics.put("uptime", formatUptime(System.currentTimeMillis() - startTime));
        metrics.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        metrics.put("processors", runtime.availableProcessors());

        return metrics;
    }

    public Map<String, Object> getDatabaseHealth() {
        Map<String, Object> health = new HashMap<>();

        try (Connection connection = DatabaseSingleton.getInstance().getConn()) {
            boolean isValid = connection.isValid(5);
            health.put("status", isValid ? "UP" : "DOWN");
            health.put("database", connection.getMetaData().getDatabaseProductName());
            health.put("version", connection.getMetaData().getDatabaseProductVersion());
            health.put("url", connection.getMetaData().getURL());
            health.put("connection_test", isValid);
            health.put("last_check", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (SQLException e) {
            health.put("status", "DOWN");
            health.put("error", e.getMessage());
            health.put("last_check", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        return health;
    }

    public Map<String, Object> getS3Health() {
        Map<String, Object> health = new HashMap<>();

        try {
            boolean s3Available = s3Service.testConnection();
            health.put("status", s3Available ? "UP" : "DOWN");
            health.put("service", "Amazon S3");
            health.put("last_check", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (Exception e) {
            health.put("status", "DOWN");
            health.put("error", e.getMessage());
            health.put("last_check", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        return health;
    }

    public Map<String, Object> getApplicationHealth() {
        Map<String, Object> health = new HashMap<>();
        Map<String, Object> database = getDatabaseHealth();
        Map<String, Object> s3 = getS3Health();

        boolean allHealthy = "UP".equals(database.get("status")) && "UP".equals(s3.get("status"));

        health.put("status", allHealthy ? "UP" : "DEGRADED");
        health.put("components", Map.of(
            "database", database,
            "s3", s3
        ));
        health.put("overall_health", allHealthy);
        health.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return health;
    }

    public Map<String, Object> getComprehensiveReport() {
        Map<String, Object> report = new HashMap<>();

        report.put("system", getSystemMetrics());
        report.put("health", getApplicationHealth());
        report.put("services", Map.of(
            "database", getDatabaseHealth(),
            "s3", getS3Health()
        ));

        return report;
    }

    private String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024));
        return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
    }

    private String formatUptime(long uptimeMillis) {
        long seconds = uptimeMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return String.format("%d jours, %d heures, %d minutes",
                days, hours % 24, minutes % 60);
        } else if (hours > 0) {
            return String.format("%d heures, %d minutes", hours, minutes % 60);
        } else if (minutes > 0) {
            return String.format("%d minutes, %d secondes", minutes, seconds % 60);
        } else {
            return String.format("%d secondes", seconds);
        }
    }
}
