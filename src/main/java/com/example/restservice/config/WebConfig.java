package com.example.restservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Autowired
    private CsrfMonitoringInterceptor csrfMonitoringInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders("X-XSRF-TOKEN"); // Exposer le header CSRF pour le front-end
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/actuator/health", "/actuator/info", "/error");

        registry.addInterceptor(csrfMonitoringInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/actuator/**", "/csrf-token", "/csrf-info");
    }
}
