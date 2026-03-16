package br.com.empresaweb.empresa_api.controllers;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.dtos.FuncionarioRequestDTO;
import br.com.empresaweb.empresa_api.services.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/V1/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // No pageable pode passar size=10, page=0, etc...
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(Pageable pageable) {
        Page<Funcionario> result = this.funcionarioService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable UUID id) throws Exception {
        var result = this.funcionarioService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody FuncionarioRequestDTO request) throws Exception {
        var result = this.funcionarioService.create(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody FuncionarioRequestDTO request) throws Exception {
        var result = this.funcionarioService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        this.funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
