package com.clinica.consultas_api.services;

import com.clinica.consultas_api.domain.entities.Paciente;
import com.clinica.consultas_api.domain.repositories.PacienteRepository;
import com.clinica.consultas_api.dtos.request.PacienteRequestDto;
import com.clinica.consultas_api.infra.exceptions.BadRequestException;
import com.clinica.consultas_api.infra.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Page<Paciente> findAll(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }

    public Paciente findById(UUID id) throws NotFoundException {
        var pacienteResult = this.pacienteRepository.findById(id);

        if(pacienteResult.isEmpty()) {
            throw new NotFoundException("Paciente não encontrado");
        }

        return pacienteResult.get();
    }

    public Paciente create(PacienteRequestDto pacienteDto) throws BadRequestException {
        var pacienteResult = this.pacienteRepository.findByCpf(pacienteDto.cpf());

        if(pacienteResult != null) {
            throw  new BadRequestException("Paciente já cadastrado");
        }

        var paciente = new Paciente();
        paciente.setCpf(pacienteDto.cpf());
        paciente.setNome(pacienteDto.nome());
        this.pacienteRepository.save(paciente);

        return paciente;
    }

    public Paciente update(UUID id, PacienteRequestDto pacienteDto) throws NotFoundException, BadRequestException {
        var pacienteResult = this.pacienteRepository.findById(id);

        if(pacienteResult.isEmpty()) {
            throw new NotFoundException("Paciente não encontrado");
        }

        var paciente = pacienteResult.get();
        paciente.setCpf(pacienteDto.cpf());
        paciente.setNome(pacienteDto.nome());
        this.pacienteRepository.save(paciente);

        return paciente;
    }

    public void delete(UUID id) throws NotFoundException {
        var pacienteResult = this.pacienteRepository.findById(id);

        if(pacienteResult.isEmpty()) {
            throw new NotFoundException("Paciente não encontrado");
        }

        pacienteRepository.delete(pacienteResult.get());
    }

}
