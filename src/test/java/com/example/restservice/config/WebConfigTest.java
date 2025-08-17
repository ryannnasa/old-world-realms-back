package com.example.restservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.*;

class WebConfigTest {

    @Test
    void testWebConfigExists() {
        WebConfig webConfig = new WebConfig();
        assertNotNull(webConfig);
    }

    @Test
    void testImplementsWebMvcConfigurer() {
        WebConfig webConfig = new WebConfig();
        assertTrue(webConfig instanceof WebMvcConfigurer);
    }
}
