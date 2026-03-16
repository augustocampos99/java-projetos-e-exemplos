package br.com.empresaweb.empresa_api.domain.entities;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;
import br.com.empresaweb.empresa_api.domain.vo.Endereco;

import java.time.LocalDateTime;
import java.util.UUID;

public class Funcionario {

    private UUID id;
    private String cpf;
    private String nome;
    private String cargo;
    private StatusEnum status;
    private Endereco endereco;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Funcionario() {}

    public Funcionario(UUID id, String cpf, String nome, String cargo, StatusEnum status) {
        if(cpf == null || cpf.length() < 11) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.status = status;
    }
}
