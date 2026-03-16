package br.com.empresaweb.empresa_api.application.usecases;

import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;
import br.com.empresaweb.empresa_api.domain.entities.Funcionario;

import java.util.List;

public class ListarFuncionario {

    public final IFuncionarioRepository funcionarioRepository;

    public ListarFuncionario(IFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }


}
