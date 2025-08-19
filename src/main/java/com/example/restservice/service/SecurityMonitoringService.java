package com.example.restservice.service;

import org.springframework.stereotype.Service;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SecurityMonitoringService {

    private static final Logger logger = Logger.getLogger(SecurityMonitoringService.class.getName());

    private final Map<String, AtomicInteger> failedAttempts = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> blockedIps = new ConcurrentHashMap<>();

    // Détection de patterns malveillants
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i)(union|select|insert|update|delete|drop|create|alter|script)"
    );

    private static final Pattern XSS_PATTERN = Pattern.compile(
        "(?i)(<script|javascript:|alert\\(|confirm\\()"
    );

    public void logSecurityEvent(String event, String details, String ipAddress) {
        String logMessage = String.format("[SECURITY] %s - IP: %s - Details: %s - Time: %s",
            event, ipAddress, details, LocalDateTime.now());
        logger.warning(logMessage);
        System.out.println(logMessage); // Pour debug
    }

    public boolean detectSqlInjection(String input) {
        if (input == null) return false;
        boolean detected = SQL_INJECTION_PATTERN.matcher(input).find();
        if (detected) {
            logSecurityEvent("SQL_INJECTION_ATTEMPT", input, "unknown");
        }
        return detected;
    }

    public boolean detectXss(String input) {
        if (input == null) return false;
        boolean detected = XSS_PATTERN.matcher(input).find();
        if (detected) {
            logSecurityEvent("XSS_ATTEMPT", input, "unknown");
        }
        return detected;
    }

    public void validateInput(String input, String fieldName) throws SecurityException {
        if (input == null) return;

        if (detectSqlInjection(input)) {
            throw new SecurityException("Tentative d'injection SQL détectée dans le champ: " + fieldName);
        }

        if (detectXss(input)) {
            throw new SecurityException("Tentative d'attaque XSS détectée dans le champ: " + fieldName);
        }

        if (input.length() > 5000) {
            logSecurityEvent("SUSPICIOUS_INPUT_LENGTH", "Input trop long: " + input.length(), "unknown");
            throw new SecurityException("Input trop long pour le champ: " + fieldName);
        }
    }

    public void recordSuspiciousActivity(String activity, String ipAddress) {
        AtomicInteger attempts = failedAttempts.computeIfAbsent(ipAddress, k -> new AtomicInteger(0));
        int currentAttempts = attempts.incrementAndGet();

        logSecurityEvent("SUSPICIOUS_ACTIVITY", activity + " (attempt " + currentAttempts + ")", ipAddress);

        if (currentAttempts >= 10) { // Seuil
            blockedIps.put(ipAddress, LocalDateTime.now().plusMinutes(30));
            logSecurityEvent("IP_BLOCKED", "Trop d'activités suspectes", ipAddress);
        }
    }

    public boolean isIpBlocked(String ipAddress) {
        LocalDateTime blockedUntil = blockedIps.get(ipAddress);
        if (blockedUntil != null) {
            if (LocalDateTime.now().isBefore(blockedUntil)) {
                return true;
            } else {
                blockedIps.remove(ipAddress);
                failedAttempts.remove(ipAddress);
            }
        }
        return false;
    }

    public Map<String, Object> getSecurityStats() {
        Map<String, Object> stats = new ConcurrentHashMap<>();
        stats.put("blocked_ips", blockedIps.size());
        stats.put("total_suspicious_ips", failedAttempts.size());
        stats.put("last_check", LocalDateTime.now());
        return stats;
    }

    public void validateCsrfToken(String token, String expectedToken, String ipAddress) {
        if (token == null || expectedToken == null) {
            logSecurityEvent("CSRF_TOKEN_MISSING", "Token or expected token is null", ipAddress);
            throw new SecurityException("Token CSRF manquant");
        }

        if (!token.equals(expectedToken)) {
            logSecurityEvent("CSRF_TOKEN_MISMATCH", "Invalid CSRF token provided", ipAddress);
            recordSuspiciousActivity("Invalid CSRF token", ipAddress);
            throw new SecurityException("Token CSRF invalide");
        }

        logSecurityEvent("CSRF_TOKEN_VALID", "CSRF token validation successful", ipAddress);
    }

    public void logCsrfViolation(String details, String ipAddress) {
        logSecurityEvent("CSRF_VIOLATION", details, ipAddress);
        recordSuspiciousActivity("CSRF violation: " + details, ipAddress);
    }
}
