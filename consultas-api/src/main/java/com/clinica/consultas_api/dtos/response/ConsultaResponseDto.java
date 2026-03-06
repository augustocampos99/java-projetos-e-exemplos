package com.clinica.consultas_api.dtos.response;

import com.clinica.consultas_api.domain.entities.Consulta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ConsultaResponseDto {
    private UUID id;
    private String nomeMedico;
    private String nomePaciente;
    private LocalDateTime dataConsulta;
    private String observacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ConsultaResponseDto parse(Consulta consulta) {
        this.id = consulta.getId();
        this.nomeMedico = consulta.getMedico().getNome();
        this.nomePaciente = consulta.getPaciente().getNome();
        this.dataConsulta = consulta.getDataConsulta();
        this.observacao = consulta.getObservacao();
        return this;
    }

    public List<ConsultaResponseDto> parse(List<Consulta> consultaList) {
        var resultList = consultaList.stream().map(consulta -> {
            var consultaDto = new ConsultaResponseDto();
            consultaDto.setId(consulta.getId());
            consultaDto.setNomeMedico(consulta.getMedico().getNome());
            consultaDto.setNomePaciente(consulta.getPaciente().getNome());
            consultaDto.setDataConsulta(consulta.getDataConsulta());
            consultaDto.setObservacao(consulta.getObservacao());
            return consultaDto;
        }).toList();

        return resultList;
    }


}
