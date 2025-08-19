package com.example.restservice.service;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CsrfTokenService {

    /**
     * Extrait le token CSRF de la requête (header ou paramètre)
     */
    public String extractCsrfToken(HttpServletRequest request) {
        // Vérifier d'abord le header X-XSRF-TOKEN
        String tokenFromHeader = request.getHeader("X-XSRF-TOKEN");
        if (tokenFromHeader != null && !tokenFromHeader.isEmpty()) {
            return tokenFromHeader;
        }

        // Ensuite vérifier le paramètre _csrf
        String tokenFromParam = request.getParameter("_csrf");
        if (tokenFromParam != null && !tokenFromParam.isEmpty()) {
            return tokenFromParam;
        }

        // Enfin vérifier le cookie XSRF-TOKEN
        return extractTokenFromCookie(request);
    }

    /**
     * Extrait le token CSRF du cookie
     */
    private String extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            Optional<Cookie> csrfCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> "XSRF-TOKEN".equals(cookie.getName()))
                .findFirst();

            return csrfCookie.map(Cookie::getValue).orElse(null);
        }
        return null;
    }

    /**
     * Obtient le token CSRF de la requête courante
     */
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    /**
     * Valide si le token fourni correspond au token attendu
     */
    public boolean isValidToken(String providedToken, CsrfToken expectedToken) {
        if (providedToken == null || expectedToken == null) {
            return false;
        }
        return providedToken.equals(expectedToken.getToken());
    }

    /**
     * Génère les informations CSRF pour une réponse API
     */
    public CsrfTokenInfo createTokenInfo(CsrfToken token) {
        if (token == null) {
            return null;
        }

        return new CsrfTokenInfo(
            token.getToken(),
            token.getHeaderName(),
            token.getParameterName(),
            "XSRF-TOKEN" // Cookie name
        );
    }

    /**
     * Classe pour encapsuler les informations du token CSRF
     */
    public static class CsrfTokenInfo {
        private final String token;
        private final String headerName;
        private final String parameterName;
        private final String cookieName;

        public CsrfTokenInfo(String token, String headerName, String parameterName, String cookieName) {
            this.token = token;
            this.headerName = headerName;
            this.parameterName = parameterName;
            this.cookieName = cookieName;
        }

        // Getters
        public String getToken() { return token; }
        public String getHeaderName() { return headerName; }
        public String getParameterName() { return parameterName; }
        public String getCookieName() { return cookieName; }
    }
}
