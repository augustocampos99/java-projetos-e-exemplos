package com.clinica.consultas_api.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PacienteRequestDto(

    @NotBlank(message = "cpf é obrigatório")
    String cpf,

    @NotBlank(message = "nome é obrigatório")
    String nome
){}

