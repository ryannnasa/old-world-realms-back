package com.example.restservice.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LoggingService {

    private final Queue<Map<String, Object>> recentEvents = new ConcurrentLinkedQueue<>();
    private final int MAX_RECENT_EVENTS = 100;
    private final Path logDirectory = Paths.get("logs");
    private final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingService() {
        try {
            Files.createDirectories(logDirectory);
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du dossier logs : " + e.getMessage());
        }
    }

    public void logEvent(String level, String category, String message, Map<String, Object> metadata) {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> event = new HashMap<>();
        event.put("timestamp", now.format(timestampFormatter));
        event.put("level", level);
        event.put("category", category);
        event.put("message", message);
        event.put("metadata", metadata != null ? metadata : new HashMap<>());

        // Ajouter à la queue des événements récents
        recentEvents.offer(event);
        if (recentEvents.size() > MAX_RECENT_EVENTS) {
            recentEvents.poll();
        }

        // Écrire dans le fichier de log
        writeToLogFile(event);
    }

    private void writeToLogFile(Map<String, Object> event) {
        try {
            String today = LocalDateTime.now().format(fileFormatter);
            Path logFile = logDirectory.resolve("monitoring-" + today + ".log");

            String logLine = String.format("[%s] [%s] [%s] %s - %s%n",
                event.get("timestamp"),
                event.get("level"),
                event.get("category"),
                event.get("message"),
                event.get("metadata")
            );

            Files.write(logFile, logLine.getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du log : " + e.getMessage());
        }
    }

    public void logInfo(String category, String message) {
        logEvent("INFO", category, message, null);
    }

    public void logInfo(String category, String message, Map<String, Object> metadata) {
        logEvent("INFO", category, message, metadata);
    }

    public void logWarning(String category, String message) {
        logEvent("WARNING", category, message, null);
    }

    public void logWarning(String category, String message, Map<String, Object> metadata) {
        logEvent("WARNING", category, message, metadata);
    }

    public void logError(String category, String message) {
        logEvent("ERROR", category, message, null);
    }

    public void logError(String category, String message, Map<String, Object> metadata) {
        logEvent("ERROR", category, message, metadata);
    }

    public void logCritical(String category, String message) {
        logEvent("CRITICAL", category, message, null);
    }

    public void logCritical(String category, String message, Map<String, Object> metadata) {
        logEvent("CRITICAL", category, message, metadata);
    }

    public List<Map<String, Object>> getRecentEvents() {
        return new ArrayList<>(recentEvents);
    }

    public List<Map<String, Object>> getRecentEventsByLevel(String level) {
        return recentEvents.stream()
            .filter(event -> level.equals(event.get("level")))
            .collect(ArrayList::new, (list, event) -> list.add(event), (list1, list2) -> list1.addAll(list2));
    }

    public List<Map<String, Object>> getRecentEventsByCategory(String category) {
        return recentEvents.stream()
            .filter(event -> category.equals(event.get("category")))
            .collect(ArrayList::new, (list, event) -> list.add(event), (list1, list2) -> list1.addAll(list2));
    }

    public Map<String, Object> getLoggingStatistics() {
        Map<String, Long> levelCounts = new HashMap<>();
        Map<String, Long> categoryCounts = new HashMap<>();

        for (Map<String, Object> event : recentEvents) {
            String level = (String) event.get("level");
            String category = (String) event.get("category");

            levelCounts.put(level, levelCounts.getOrDefault(level, 0L) + 1);
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0L) + 1);
        }

        return Map.of(
            "total_events", recentEvents.size(),
            "by_level", levelCounts,
            "by_category", categoryCounts,
            "max_capacity", MAX_RECENT_EVENTS,
            "log_directory", logDirectory.toString()
        );
    }

    public List<String> getAvailableLogFiles() {
        try {
            return Files.list(logDirectory)
                .filter(path -> path.getFileName().toString().startsWith("monitoring-"))
                .map(path -> path.getFileName().toString())
                .sorted(Collections.reverseOrder())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
