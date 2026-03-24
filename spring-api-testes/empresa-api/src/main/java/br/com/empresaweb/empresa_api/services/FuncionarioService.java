package br.com.empresaweb.empresa_api.services;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.dtos.FuncionarioRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    public List<Funcionario> findAll() {
        return List.of(
                new Funcionario(UUID.randomUUID(), "12345678901", "José Campos", LocalDateTime.now()),
                new Funcionario(UUID.randomUUID(), "32165498732", "Ana Paula", LocalDateTime.now()),
                new Funcionario(UUID.randomUUID(), "98765432103", "Chris Souza", LocalDateTime.now())
            );
    }

    public Funcionario findById(UUID id) {
        return new Funcionario(UUID.randomUUID(), "12345678901", "José Campos", LocalDateTime.now());
    }

    public Funcionario create(FuncionarioRequestDTO funcionarioDTO) {
        return new Funcionario();
    }

    public Funcionario update(UUID id, FuncionarioRequestDTO funcionarioDTO) {
        return new Funcionario(UUID.randomUUID(), funcionarioDTO.cpf(), funcionarioDTO.nome(), LocalDateTime.now() );
    }

    public void delete(UUID id) {
    }

}
