package com.clinica.consultas_api.controllers;

import com.clinica.consultas_api.domain.entities.Medico;
import com.clinica.consultas_api.dtos.request.MedicoRequestDto;
import com.clinica.consultas_api.services.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/V1/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // No pageable pode passar size=10, page=0, etc...
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(Pageable pageable) {
        Page<Medico> result = this.medicoService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable UUID id) throws Exception {
        var result = this.medicoService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody MedicoRequestDto request) throws Exception {
        var result = this.medicoService.create(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody MedicoRequestDto request) throws Exception {
        var result = this.medicoService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        this.medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
