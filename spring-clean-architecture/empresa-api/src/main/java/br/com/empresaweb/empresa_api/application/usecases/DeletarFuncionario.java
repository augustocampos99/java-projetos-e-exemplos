package br.com.empresaweb.empresa_api.application.usecases;

import br.com.empresaweb.empresa_api.application.exceptions.NotFoundException;
import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;

import java.util.UUID;

public class DeletarFuncionario {

    public final IFuncionarioRepository funcionarioRepository;

    public DeletarFuncionario(IFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void deletar(UUID id) throws NotFoundException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if (funcionarioResult == null) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        this.funcionarioRepository.delete(funcionarioResult.getId());
    }

}