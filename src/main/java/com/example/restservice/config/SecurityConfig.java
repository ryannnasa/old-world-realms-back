package com.example.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuration du handler CSRF
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
            // Configuration CSRF activée avec token en cookie
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers(
                    // Exclure certains endpoints de la protection CSRF
                    "/api/public/**",
                    "/actuator/**",
                    "/csrf-token", // Endpoint pour récupérer le token CSRF
                    "/csrf-info",  // Informations sur CSRF
                    "/csrf-validate" // Validation du token CSRF
                )
            )

            // Configuration des sessions - changement vers NEVER pour supporter CSRF
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

            // Configuration des autorisations
            .authorizeHttpRequests(auth -> auth
                // Endpoints de santé et surveillance
                .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                .requestMatchers("/csrf-token").permitAll() // Endpoint pour récupérer le token CSRF

                // Endpoints publics de votre application
                .requestMatchers("/player/**", "/player").permitAll()
                .requestMatchers("/scenario/**", "/scenario").permitAll()
                .requestMatchers("/battlereport/**", "/battlereport").permitAll()
                .requestMatchers("/battle-report/**", "/battle-report").permitAll()
                .requestMatchers("/status/**", "/status").permitAll()
                .requestMatchers("/monitoring/**", "/monitoring").permitAll()
                .requestMatchers("/logs/**", "/logs").permitAll()
                .requestMatchers("/dashboard/**", "/dashboard").permitAll()
                .requestMatchers("/upload/**", "/download/**").permitAll()
                .requestMatchers("/s3/**").permitAll()
                .requestMatchers("/image-url/**").permitAll()

                // Autoriser tous les autres endpoints
                .anyRequest().permitAll()
            )

            // Headers de sécurité basiques
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.deny())
                .contentTypeOptions(contentType -> {})
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
