package br.com.empresaweb.empresa_api.domain.entities;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;
import br.com.empresaweb.empresa_api.domain.vo.Endereco;

import java.util.UUID;

public class FuncionarioFactory {

    private Funcionario funcionario;

    public FuncionarioFactory criarFuncionarioBase(UUID id, String cpf, String nome, String cargo, StatusEnum status) {
        this.funcionario = new Funcionario(id, cpf, nome, cargo, status);
        return this;
    }

    public FuncionarioFactory adicionarEndereco(String cep, String logradouro, String numero) {
        this.funcionario.setEndereco(new Endereco(cep, logradouro, numero));
        return this;
    }

    public Funcionario build() {
        return this.funcionario;
    }

}
