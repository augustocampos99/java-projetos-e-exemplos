package br.com.empresaweb.empresa_api.application.usecases;

import br.com.empresaweb.empresa_api.application.dtos.FuncionarioRequestDTO;
import br.com.empresaweb.empresa_api.application.exceptions.BadRequestException;
import br.com.empresaweb.empresa_api.application.exceptions.NotFoundException;
import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;
import br.com.empresaweb.empresa_api.domain.entities.Funcionario;

import java.util.UUID;

public class AtualizarFuncionario {

    public final IFuncionarioRepository funcionarioRepository;

    public AtualizarFuncionario(IFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario atualizar(UUID id, FuncionarioRequestDTO funcionarioDTO) throws NotFoundException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if(funcionarioResult == null) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        funcionarioResult.setCpf(this.limparCPF(funcionarioDTO.cpf()));
        funcionarioResult.setNome(funcionarioDTO.nome());
        funcionarioResult.setCargo(funcionarioDTO.cargo());
        funcionarioResult.setStatus(funcionarioDTO.status());
        this.funcionarioRepository.update(id, funcionarioResult);

        return funcionarioResult;
    }

    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }
}
