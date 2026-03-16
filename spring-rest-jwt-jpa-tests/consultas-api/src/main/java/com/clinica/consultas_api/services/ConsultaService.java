package com.clinica.consultas_api.services;

import com.clinica.consultas_api.domain.entities.Consulta;
import com.clinica.consultas_api.domain.repositories.ConsultaRepository;
import com.clinica.consultas_api.domain.repositories.MedicoRepository;
import com.clinica.consultas_api.domain.repositories.PacienteRepository;
import com.clinica.consultas_api.dtos.request.ConsultaRequestDto;
import com.clinica.consultas_api.dtos.response.ConsultaResponseDto;
import com.clinica.consultas_api.infra.exceptions.BadRequestException;
import com.clinica.consultas_api.infra.exceptions.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(
        ConsultaRepository consultaRepository,
        MedicoRepository medicoRepository,
        PacienteRepository pacienteRepository)
    {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<ConsultaResponseDto> findAll(Pageable pageable) {
        return new ConsultaResponseDto().parse(this.consultaRepository.findAll(pageable).toList());
    }

    public ConsultaResponseDto findById(UUID id) throws NotFoundException {
        var consultaResult = this.consultaRepository.findById(id);

        if(consultaResult.isEmpty()) {
            throw new NotFoundException("Consulta não encontrada");
        }

        return new ConsultaResponseDto().parse(consultaResult.get());
    }

    public ConsultaResponseDto create(ConsultaRequestDto consultaDto) throws BadRequestException {
        var medicoResult = this.medicoRepository.findById(consultaDto.idMedico());
        if(medicoResult.isEmpty()) {
            throw  new BadRequestException("Medico não encontrado");
        }

        var pacienteResult = this.pacienteRepository.findById(consultaDto.idPaciente());
        if(pacienteResult.isEmpty()) {
            throw  new BadRequestException("Paciente não encontrado");
        }

        var consulta = new Consulta();
        consulta.setIdMedico(consultaDto.idMedico());
        consulta.setIdPaciente(consultaDto.idPaciente());
        consulta.setDataConsulta(consultaDto.dataConsulta());
        consulta.setObservacao(consultaDto.observacao());
        this.consultaRepository.save(consulta);

        return new ConsultaResponseDto().parse(consulta);
    }

    public ConsultaResponseDto update(UUID id, ConsultaRequestDto consultaDto) throws NotFoundException, BadRequestException {
        var consultaResult = this.consultaRepository.findById(id);
        if(consultaResult.isEmpty()) {
            throw new NotFoundException("Consulta não encontrada");
        }

        var medicoResult = this.medicoRepository.findById(consultaDto.idMedico());
        if(medicoResult.isEmpty()) {
            throw  new BadRequestException("Medico não encontrado");
        }

        var pacienteResult = this.pacienteRepository.findById(consultaDto.idPaciente());
        if(pacienteResult.isEmpty()) {
            throw  new BadRequestException("Paciente não encontrado");
        }

        var consulta = consultaResult.get();
        consulta.setIdMedico(consultaDto.idMedico());
        consulta.setIdPaciente(consultaDto.idPaciente());
        consulta.setDataConsulta(consultaDto.dataConsulta());
        consulta.setObservacao(consultaDto.observacao());
        this.consultaRepository.save(consulta);

        return new ConsultaResponseDto().parse(consulta);
    }

    public void delete(UUID id) throws NotFoundException {
        var consultaResult = this.consultaRepository.findById(id);

        if(consultaResult.isEmpty()) {
            throw new NotFoundException("Consulta não encontrada");
        }

        consultaRepository.delete(consultaResult.get());
    }

}
