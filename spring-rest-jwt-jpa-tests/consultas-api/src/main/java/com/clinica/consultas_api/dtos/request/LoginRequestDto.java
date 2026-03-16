package com.clinica.consultas_api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto (
        @NotBlank(message = "username é obrigatório")
        String username,
        @NotBlank(message = "password é obrigatório")
        String password
) { }
