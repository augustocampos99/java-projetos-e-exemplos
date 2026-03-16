package br.com.empresaweb.empresa_api.application.dtos;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequestDTO(
    @NotBlank(message = "cpf é obrigatório")
    String cpf,

    @NotBlank(message = "nome é obrigatório")
    String nome,

    @NotBlank(message = "cargo é obrigatório")
    String cargo,

    @NotNull(message = "status é obrigatório")
    StatusEnum status
) { }
