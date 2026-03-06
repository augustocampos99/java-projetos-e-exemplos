package com.clinica.consultas_api.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Necessário para carregar o contexto completo do spring
@AutoConfigureMockMvc // Necessário para carregar o MockMvc
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar o status 403")
    public void deveRetornarStatus403Test() throws Exception {
        var response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/V1/pacientes"))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar o status 200")
    @WithMockUser
    public void deveRetornarStatus200Test() throws Exception {

        var response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/V1/pacientes?page=0&size=10")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                        .andExpect(status().isOk());
    }

}