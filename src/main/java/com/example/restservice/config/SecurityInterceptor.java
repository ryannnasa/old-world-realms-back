package com.example.restservice.config;

import com.example.restservice.service.SecurityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityMonitoringService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = getClientIpAddress(request);

        if (securityService.isIpBlocked(clientIp)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"IP temporarily blocked due to security violations\"}");
            return false;
        }

        validateRequestParameters(request, clientIp);
        validateRequestHeaders(request, clientIp);

        return true;
    }

    private void validateRequestParameters(HttpServletRequest request, String clientIp) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);

            try {
                securityService.validateInput(paramValue, paramName);
            } catch (SecurityException e) {
                securityService.logSecurityEvent("MALICIOUS_PARAMETER",
                    String.format("Parameter: %s, Value: %s, Error: %s", paramName, paramValue, e.getMessage()),
                    clientIp);
                throw e;
            }
        }
    }

    private void validateRequestHeaders(HttpServletRequest request, String clientIp) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            if (securityService.detectXss(headerValue) || securityService.detectSqlInjection(headerValue)) {
                securityService.logSecurityEvent("MALICIOUS_HEADER",
                    String.format("Header: %s, Value: %s", headerName, headerValue),
                    clientIp);
            }
        }
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
