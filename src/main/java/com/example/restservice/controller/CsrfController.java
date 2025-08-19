package com.example.restservice.controller;

import com.example.restservice.service.CsrfTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CsrfController {

    @Autowired
    private CsrfTokenService csrfTokenService;

    @GetMapping("/csrf-token")
    public Map<String, Object> getCsrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = csrfTokenService.getCsrfToken(request);
        Map<String, Object> response = new HashMap<>();

        if (csrfToken != null) {
            CsrfTokenService.CsrfTokenInfo tokenInfo = csrfTokenService.createTokenInfo(csrfToken);
            response.put("token", tokenInfo.getToken());
            response.put("headerName", tokenInfo.getHeaderName());
            response.put("parameterName", tokenInfo.getParameterName());
            response.put("cookieName", tokenInfo.getCookieName());
            response.put("success", true);
        } else {
            response.put("error", "CSRF token not available");
            response.put("success", false);
        }
        return response;
    }

    @GetMapping("/csrf-info")
    public Map<String, Object> getCsrfInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("csrfEnabled", true);
        info.put("cookieName", "XSRF-TOKEN");
        info.put("headerName", "X-XSRF-TOKEN");
        info.put("parameterName", "_csrf");
        info.put("instructions", Map.of(
            "frontend", "Le token CSRF est automatiquement défini dans le cookie XSRF-TOKEN. Incluez-le dans vos requêtes POST/PUT/DELETE via le header X-XSRF-TOKEN.",
            "curl", "curl -X POST -H 'X-XSRF-TOKEN: <token>' -H 'Content-Type: application/json' http://localhost:8080/your-endpoint",
            "javascript", "fetch('/your-endpoint', { method: 'POST', headers: { 'X-XSRF-TOKEN': document.cookie.match(/XSRF-TOKEN=([^;]+)/)?.[1] } })"
        ));
        return info;
    }

    @GetMapping("/csrf-validate")
    public Map<String, Object> validateCsrfToken(HttpServletRequest request) {
        CsrfToken expectedToken = csrfTokenService.getCsrfToken(request);
        String providedToken = csrfTokenService.extractCsrfToken(request);

        Map<String, Object> response = new HashMap<>();
        boolean isValid = csrfTokenService.isValidToken(providedToken, expectedToken);

        response.put("valid", isValid);
        response.put("expectedToken", expectedToken != null ? expectedToken.getToken() : null);
        response.put("providedToken", providedToken);
        response.put("message", isValid ? "Token CSRF valide" : "Token CSRF invalide ou manquant");

        return response;
    }
}
