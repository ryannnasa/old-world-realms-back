package com.example.restservice.controller;

import com.example.restservice.service.SecurityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityMonitoringService securityService;

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSecurityStatus() {
        return ResponseEntity.ok(securityService.getSecurityStats());
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateInput(@RequestBody Map<String, String> input) {
        try {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                securityService.validateInput(entry.getValue(), entry.getKey());
            }
            return ResponseEntity.ok("Input validé avec succès");
        } catch (SecurityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> securityHealth() {
        return ResponseEntity.ok("Service de sécurité opérationnel");
    }
}
