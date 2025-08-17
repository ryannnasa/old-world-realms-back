package com.example.restservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StatusControllerTest {

    @InjectMocks
    private StatusController statusController;

    @Test
    void testGetStatus() {
        String result = statusController.getStatus();
        assertEquals("OK", result);
    }

    @Test
    void testGetStatusEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();

        mockMvc.perform(get("/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}
