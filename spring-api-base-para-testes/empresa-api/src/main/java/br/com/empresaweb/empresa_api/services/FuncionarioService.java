package br.com.empresaweb.empresa_api.services;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.domain.repositories.FuncionarioRepository;
import br.com.empresaweb.empresa_api.dtos.FuncionarioRequestDTO;
import br.com.empresaweb.empresa_api.infra.exceptions.BadRequestException;
import br.com.empresaweb.empresa_api.infra.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<Funcionario> findAll(Pageable pageable) {
        return this.funcionarioRepository.findAll(pageable);
    }

    public Funcionario findById(UUID id) throws NotFoundException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if(funcionarioResult.isEmpty()) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        return funcionarioResult.get();
    }

    public Funcionario create(FuncionarioRequestDTO funcionarioDTO) throws BadRequestException {
        var funcionarioResult = this.funcionarioRepository.findByCpf(this.limparCPF(funcionarioDTO.cpf()));

        if(funcionarioResult != null) {
            throw  new BadRequestException("Funcionário já cadastrado");
        }

        var funcionario = new Funcionario();
        funcionario.setCpf(this.limparCPF(funcionarioDTO.cpf()));
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setCargo(funcionarioDTO.cargo());
        funcionario.setStatus(funcionarioDTO.status());
        funcionario.setDataCriacao(LocalDateTime.now());
        funcionario.setDataAtualizacao(LocalDateTime.now());

        this.funcionarioRepository.save(funcionario);

        return funcionario;
    }

    public Funcionario update(UUID id, FuncionarioRequestDTO funcionarioDTO) throws NotFoundException, BadRequestException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if(funcionarioResult.isEmpty()) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        var funcionario = funcionarioResult.get();
        funcionario.setCpf(this.limparCPF(funcionarioDTO.cpf()));
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setCargo(funcionarioDTO.cargo());
        funcionario.setStatus(funcionarioDTO.status());
        funcionario.setDataAtualizacao(LocalDateTime.now());
        this.funcionarioRepository.save(funcionario);

        return funcionario;
    }

    public void delete(UUID id) throws NotFoundException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if(funcionarioResult.isEmpty()) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        this.funcionarioRepository.delete(funcionarioResult.get());
    }

    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

}
