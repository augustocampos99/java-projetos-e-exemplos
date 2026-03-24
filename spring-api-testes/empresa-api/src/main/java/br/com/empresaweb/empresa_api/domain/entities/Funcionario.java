package br.com.empresaweb.empresa_api.domain.entities;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class Funcionario {

    private UUID id;

    private String cpf;

    private String nome;

    private LocalDateTime dataCriacao;

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Funcionario(){}

    public Funcionario(UUID id, String cpf, String nome, LocalDateTime dataCriacao) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }
}
