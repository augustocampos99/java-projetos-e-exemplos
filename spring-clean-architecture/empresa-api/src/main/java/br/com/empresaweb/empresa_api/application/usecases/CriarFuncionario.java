package br.com.empresaweb.empresa_api.application.usecases;

import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;
import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.domain.vo.Endereco;
import br.com.empresaweb.empresa_api.application.dtos.FuncionarioRequestDTO;
import br.com.empresaweb.empresa_api.application.exceptions.BadRequestException;

public class CriarFuncionario {

    public final IFuncionarioRepository funcionarioRepository;

    public CriarFuncionario(IFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario cadastrar(FuncionarioRequestDTO funcionarioDTO) throws BadRequestException {
        var funcionarioResult = this.funcionarioRepository.findByCpf(this.limparCPF(funcionarioDTO.cpf()));
        if(funcionarioResult != null) {
            throw  new BadRequestException("Funcionário já cadastrado");
        }

        var funcionario = new Funcionario(
                null,
                funcionarioDTO.cpf(),
                funcionarioDTO.nome(),
                funcionarioDTO.cargo(),
                funcionarioDTO.status()
        );
        this.funcionarioRepository.create(funcionario);

        return funcionario;
    }


    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }
}
