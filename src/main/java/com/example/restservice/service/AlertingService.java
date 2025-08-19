package com.example.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Service
public class AlertingService {

    private static final Logger logger = Logger.getLogger(AlertingService.class.getName());

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private LoggingService loggingService;

    private final Map<String, Object> lastKnownStatus = new ConcurrentHashMap<>();
    private int consecutiveFailures = 0;
    private static final int FAILURE_THRESHOLD = 3;

    @Scheduled(fixedRate = 60000) // Vérification toutes les minutes
    public void performHealthCheck() {
        try {
            Map<String, Object> currentHealth = monitoringService.getApplicationHealth();
            analyzeHealthStatus(currentHealth);
        } catch (Exception e) {
            logger.severe("Erreur lors de la vérification de santé : " + e.getMessage());
            consecutiveFailures++;
            if (consecutiveFailures >= FAILURE_THRESHOLD) {
                triggerCriticalAlert("Système de monitoring défaillant", e.getMessage());
            }
        }
    }

    @Scheduled(fixedRate = 300000) // Vérification des métriques toutes les 5 minutes
    public void performMetricsCheck() {
        try {
            Map<String, Object> metrics = monitoringService.getSystemMetrics();
            analyzeSystemMetrics(metrics);
        } catch (Exception e) {
            logger.warning("Erreur lors de la collecte des métriques : " + e.getMessage());
        }
    }

    private void analyzeHealthStatus(Map<String, Object> currentHealth) {
        String currentStatus = (String) currentHealth.get("status");
        String previousStatus = (String) lastKnownStatus.get("status");

        if ("DOWN".equals(currentStatus) || "DEGRADED".equals(currentStatus)) {
            consecutiveFailures++;
            if (consecutiveFailures >= FAILURE_THRESHOLD) {
                triggerHealthAlert(currentStatus, currentHealth);
            }
        } else if ("UP".equals(currentStatus)) {
            if (consecutiveFailures > 0) {
                triggerRecoveryAlert(previousStatus, currentStatus);
            }
            consecutiveFailures = 0;
        }

        // Analyse des composants individuels
        @SuppressWarnings("unchecked")
        Map<String, Object> components = (Map<String, Object>) currentHealth.get("components");
        if (components != null) {
            analyzeComponentHealth(components);
        }

        lastKnownStatus.putAll(currentHealth);
    }

    private void analyzeComponentHealth(Map<String, Object> components) {
        components.forEach((componentName, componentHealth) -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> health = (Map<String, Object>) componentHealth;
            String status = (String) health.get("status");

            if ("DOWN".equals(status)) {
                triggerComponentAlert(componentName, health);
            }
        });
    }

    private void analyzeSystemMetrics(Map<String, Object> metrics) {
        @SuppressWarnings("unchecked")
        Map<String, Object> memory = (Map<String, Object>) metrics.get("memory");

        if (memory != null) {
            Integer usagePercentage = (Integer) memory.get("usage_percentage");
            if (usagePercentage != null && usagePercentage > 85) {
                triggerMemoryAlert(usagePercentage, memory);
            }
        }
    }

    @Async
    public void triggerHealthAlert(String status, Map<String, Object> healthData) {
        String message = String.format(
            "ALERTE SANTÉ SYSTÈME - Statut: %s à %s",
            status,
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.severe(message);
        logger.severe("Détails: " + healthData.toString());

        // Ici on pourrait ajouter l'envoi d'emails, notifications Slack, etc.
        sendNotification("HEALTH_ALERT", message, healthData);
    }

    @Async
    public void triggerComponentAlert(String componentName, Map<String, Object> componentHealth) {
        String message = String.format(
            "ALERTE COMPOSANT - %s est DOWN à %s",
            componentName.toUpperCase(),
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.warning(message);
        logger.warning("Détails du composant: " + componentHealth.toString());

        sendNotification("COMPONENT_ALERT", message, Map.of("component", componentName, "health", componentHealth));
    }

    @Async
    public void triggerMemoryAlert(int usagePercentage, Map<String, Object> memoryData) {
        String message = String.format(
            "ALERTE MÉMOIRE - Utilisation: %d%% à %s",
            usagePercentage,
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.warning(message);
        logger.warning("Détails mémoire: " + memoryData.toString());

        sendNotification("MEMORY_ALERT", message, memoryData);
    }

    @Async
    public void triggerRecoveryAlert(String previousStatus, String currentStatus) {
        String message = String.format(
            "RÉCUPÉRATION SYSTÈME - Statut changé de %s à %s à %s",
            previousStatus,
            currentStatus,
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.info(message);
        sendNotification("RECOVERY_ALERT", message, Map.of("previous", previousStatus, "current", currentStatus));
    }

    @Async
    public void triggerCriticalAlert(String reason, String details) {
        String message = String.format(
            "ALERTE CRITIQUE - %s à %s",
            reason,
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.severe(message);
        logger.severe("Détails: " + details);

        sendNotification("CRITICAL_ALERT", message, Map.of("reason", reason, "details", details));
    }

    private void sendNotification(String alertType, String message, Map<String, Object> data) {
        // Log structuré dans le système de logging
        String logLevel = getLogLevelFromAlertType(alertType);
        loggingService.logEvent(logLevel, "ALERT", message, data);

        logger.info(String.format("Notification [%s]: %s", alertType, message));

        // Exemple d'implémentation pour log dans un fichier spécifique
        logToAlertFile(alertType, message, data);
    }

    private String getLogLevelFromAlertType(String alertType) {
        switch (alertType) {
            case "CRITICAL_ALERT":
                return "CRITICAL";
            case "HEALTH_ALERT":
            case "COMPONENT_ALERT":
                return "ERROR";
            case "MEMORY_ALERT":
                return "WARNING";
            case "RECOVERY_ALERT":
                return "INFO";
            default:
                return "INFO";
        }
    }

    private void logToAlertFile(String alertType, String message, Map<String, Object> data) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logEntry = String.format("[%s] [%s] %s - Data: %s%n",
            timestamp, alertType, message, data.toString());

        // Ici on pourrait écrire dans un fichier de logs spécifique aux alertes
        System.out.println("ALERT LOG: " + logEntry);
    }

    public Map<String, Object> getAlertingStatus() {
        return Map.of(
            "service_status", "ACTIVE",
            "consecutive_failures", consecutiveFailures,
            "failure_threshold", FAILURE_THRESHOLD,
            "last_check", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "monitoring_enabled", true
        );
    }
}
