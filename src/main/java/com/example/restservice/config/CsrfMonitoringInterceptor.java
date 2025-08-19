package com.example.restservice.config;

import com.example.restservice.service.SecurityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CsrfMonitoringInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityMonitoringService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String clientIp = getClientIpAddress(request);

        // Vérifier les méthodes qui nécessitent une protection CSRF
        if ("POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method) || "PATCH".equals(method)) {

            // Exclure les endpoints publics
            String requestPath = request.getRequestURI();
            if (isPublicEndpoint(requestPath)) {
                return true;
            }

            CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            String tokenFromRequest = getTokenFromRequest(request, csrfToken);

            if (csrfToken != null && tokenFromRequest != null) {
                try {
                    securityService.validateCsrfToken(tokenFromRequest, csrfToken.getToken(), clientIp);
                } catch (SecurityException e) {
                    securityService.logCsrfViolation("CSRF token validation failed for " + requestPath, clientIp);
                    // Ne pas bloquer la requête, laisser Spring Security gérer
                }
            } else if (csrfToken == null) {
                securityService.logSecurityEvent("CSRF_TOKEN_MISSING",
                    "No CSRF token available for " + requestPath, clientIp);
            }
        }

        return true;
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/public/") ||
               path.startsWith("/actuator/") ||
               path.equals("/csrf-token") ||
               path.equals("/csrf-info");
    }

    private String getTokenFromRequest(HttpServletRequest request, CsrfToken csrfToken) {
        if (csrfToken == null) return null;

        // Vérifier d'abord le header
        String tokenFromHeader = request.getHeader(csrfToken.getHeaderName());
        if (tokenFromHeader != null) {
            return tokenFromHeader;
        }

        // Ensuite vérifier le paramètre
        return request.getParameter(csrfToken.getParameterName());
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return xForwardedForHeader.split(",")[0];
        }
    }
}
