package com.clinica.consultas_api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record MedicoRequestDto(

    @NotBlank(message = "crm é obrigatório")
    String crm,

    @NotBlank(message = "nome é obrigatório")
    String nome
){}

