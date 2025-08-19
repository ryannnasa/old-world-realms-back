package com.example.restservice.controller;

import com.example.restservice.service.CsrfTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CsrfController.class)
class CsrfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CsrfTokenService csrfTokenService;

    @Test
    void getCsrfToken_ShouldReturnValidToken() throws Exception {
        mockMvc.perform(get("/csrf-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.headerName").value("X-XSRF-TOKEN"))
                .andExpect(jsonPath("$.parameterName").value("_csrf"))
                .andExpect(jsonPath("$.cookieName").value("XSRF-TOKEN"));
    }

    @Test
    void getCsrfInfo_ShouldReturnCsrfConfiguration() throws Exception {
        mockMvc.perform(get("/csrf-info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.csrfEnabled").value(true))
                .andExpect(jsonPath("$.cookieName").value("XSRF-TOKEN"))
                .andExpect(jsonPath("$.headerName").value("X-XSRF-TOKEN"))
                .andExpect(jsonPath("$.parameterName").value("_csrf"))
                .andExpect(jsonPath("$.instructions").exists());
    }

    @Test
    @WithMockUser
    void testSecureEndpoint_WithValidCsrfToken_ShouldSucceed() throws Exception {
        mockMvc.perform(post("/api/secure-endpoint")
                        .with(csrf())
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testSecureEndpoint_WithoutCsrfToken_ShouldFail() throws Exception {
        mockMvc.perform(post("/api/secure-endpoint")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isForbidden());
    }
}
