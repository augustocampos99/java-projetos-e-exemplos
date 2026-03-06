package com.clinica.consultas_api.services;

import com.clinica.consultas_api.domain.entities.Medico;
import com.clinica.consultas_api.domain.repositories.MedicoRepository;
import com.clinica.consultas_api.dtos.request.MedicoRequestDto;
import com.clinica.consultas_api.infra.exceptions.BadRequestException;
import com.clinica.consultas_api.infra.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<Medico> findAll(Pageable pageable) {
        return this.medicoRepository.findAll(pageable);
    }

    public Medico findById(UUID id) throws NotFoundException {
        var medicoResult = this.medicoRepository.findById(id);

        if(medicoResult.isEmpty()) {
            throw new NotFoundException("Medico não encontrado");
        }

        return medicoResult.get();
    }

    public Medico create(MedicoRequestDto medicoDto) throws BadRequestException {
        var medicoResult = this.medicoRepository.findByCpf(medicoDto.crm());

        if(medicoResult != null) {
            throw  new BadRequestException("Medico já cadastrado");
        }

        var medico = new Medico();
        medico.setCrm(medicoDto.crm());
        medico.setNome(medicoDto.nome());
        this.medicoRepository.save(medico);

        return medico;
    }

    public Medico update(UUID id, MedicoRequestDto medicoDto) throws NotFoundException, BadRequestException {
        var medicoResult = this.medicoRepository.findById(id);

        if(medicoResult.isEmpty()) {
            throw new NotFoundException("Medico não encontrado");
        }

        var medico = medicoResult.get();
        medico.setCrm(medicoDto.crm());
        medico.setNome(medicoDto.nome());
        this.medicoRepository.save(medico);

        return medico;
    }

    public void delete(UUID id) throws NotFoundException {
        var medicoResult = this.medicoRepository.findById(id);

        if(medicoResult.isEmpty()) {
            throw new NotFoundException("Medico não encontrado");
        }

        medicoRepository.delete(medicoResult.get());
    }

}
