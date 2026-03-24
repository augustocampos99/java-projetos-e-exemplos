package br.com.empresaweb.empresa_api.dtos;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequestDTO(
    @NotBlank(message = "cpf é obrigatório")
    String cpf,

    @NotBlank(message = "nome é obrigatório")
    String nome
) { }
