package br.com.empresaweb.empresa_api.application.usecases;

import br.com.empresaweb.empresa_api.application.exceptions.NotFoundException;
import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;
import br.com.empresaweb.empresa_api.domain.entities.Funcionario;

import java.util.UUID;

public class BuscarFuncionarioPorID {

    public final IFuncionarioRepository funcionarioRepository;

    public BuscarFuncionarioPorID(IFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario buscar(UUID id) throws NotFoundException {
        var funcionarioResult = this.funcionarioRepository.findById(id);

        if(funcionarioResult == null) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        return this.funcionarioRepository.findById(id);
    }

}
