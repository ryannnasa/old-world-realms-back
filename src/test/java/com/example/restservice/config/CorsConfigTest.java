package com.example.restservice.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @Test
    void testCorsConfigurationExists() {
        CorsConfig corsConfig = new CorsConfig();
        assertNotNull(corsConfig);
    }

    @Test
    void testCorsConfigInstantiation() {
        assertDoesNotThrow(() -> {
            CorsConfig corsConfig = new CorsConfig();
            assertNotNull(corsConfig);
        });
    }
}
