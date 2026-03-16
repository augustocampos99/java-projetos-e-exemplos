package br.com.empresaweb.empresa_api.infra.gateways;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.infra.persistence.FuncionarioEntity;

public class FuncionarioEntityMapper {

    public FuncionarioEntity toEntity(Funcionario funcionario) {
        return new FuncionarioEntity(funcionario.getCpf(), funcionario.getNome(), funcionario.getCargo(), funcionario.getStatus());
    }

    public Funcionario toDomain(FuncionarioEntity funcionarioEntity) {
        return new Funcionario(funcionarioEntity.getId(), funcionarioEntity.getCpf(), funcionarioEntity.getNome(), funcionarioEntity.getCargo(), funcionarioEntity.getStatus());
    }

}
