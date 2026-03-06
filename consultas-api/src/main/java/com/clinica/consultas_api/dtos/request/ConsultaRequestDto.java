package com.clinica.consultas_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequestDto(

        @NotNull(message = "idMedico é obrigatório")
        UUID idMedico,

        @NotNull(message = "idPeciente é obrigatório")
        UUID idPaciente,

        @NotNull(message = "dataConsulta é obrigatório")
        LocalDateTime dataConsulta,

        String observacao
){}

