package br.com.empresaweb.empresa_api.controllers;

import br.com.empresaweb.empresa_api.application.usecases.*;
import br.com.empresaweb.empresa_api.application.dtos.FuncionarioRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/V1/funcionarios")
public class FuncionarioController {

    private final ListarFuncionario listarFuncionario;
    private final BuscarFuncionarioPorID buscarFuncionarioPorID;
    private final CriarFuncionario criarFuncionario;
    private final AtualizarFuncionario atualizarFuncionario;
    private final DeletarFuncionario deletarFuncionario;

    public FuncionarioController(ListarFuncionario listarFuncionario, BuscarFuncionarioPorID buscarFuncionarioPorID, CriarFuncionario criarFuncionario, AtualizarFuncionario atualizarFuncionario, DeletarFuncionario deletarFuncionario) {
        this.listarFuncionario = listarFuncionario;
        this.buscarFuncionarioPorID = buscarFuncionarioPorID;
        this.criarFuncionario = criarFuncionario;
        this.atualizarFuncionario = atualizarFuncionario;
        this.deletarFuncionario = deletarFuncionario;
    }


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        var result = this.listarFuncionario.listar();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable UUID id) throws Exception {
        var result = this.buscarFuncionarioPorID.buscar(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody FuncionarioRequestDTO request) throws Exception {
        var result = this.criarFuncionario.cadastrar(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody FuncionarioRequestDTO request) throws Exception {
        var result = this.atualizarFuncionario.atualizar(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        this.deletarFuncionario.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
