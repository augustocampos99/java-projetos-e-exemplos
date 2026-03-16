package br.com.empresaweb.empresa_api.infra.gateways;

import br.com.empresaweb.empresa_api.application.exceptions.NotFoundException;
import br.com.empresaweb.empresa_api.application.gateways.IFuncionarioRepository;
import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import br.com.empresaweb.empresa_api.domain.vo.Endereco;
import br.com.empresaweb.empresa_api.infra.persistence.FuncionarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FuncionarioRepositoryImpl implements IFuncionarioRepository {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioEntityMapper mapper;

    public FuncionarioRepositoryImpl(FuncionarioRepository funcionarioRepository, FuncionarioEntityMapper mapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Funcionario> findAll() {
        var result = this.funcionarioRepository.findAll().stream().map(e -> new Funcionario(
                e.getId(), e.getCpf(), e.getNome(), e.getCargo(), e.getStatus()
        ));
        return result.toList();
    }

    @Override
    public Funcionario findById(UUID id) {
        var result = this.funcionarioRepository.findById(id);
        return this.mapper.toDomain(result.get());
    }

    @Override
    public Funcionario findByCpf(String cpf) {
        var result = this.funcionarioRepository.findByCpf(cpf);
        if(result == null) {
            return null;
        }
        return this.mapper.toDomain(result);
    }

    @Override
    public Funcionario create(Funcionario funcionario) {
        var entity = this.mapper.toEntity(funcionario);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setDataAtualizacao(LocalDateTime.now());

        this.funcionarioRepository.save(entity);

        return this.mapper.toDomain(entity);
    }

    @Override
    public Funcionario update(UUID id, Funcionario funcionario) {
        var funcionarioResult = this.funcionarioRepository.findById(id).get();

        funcionarioResult.setCpf(funcionario.getCpf());
        funcionarioResult.setNome(funcionario.getNome());
        funcionarioResult.setCargo(funcionario.getCargo());
        funcionarioResult.setStatus(funcionario.getStatus());
        funcionarioResult.setDataAtualizacao(LocalDateTime.now());

        this.funcionarioRepository.save(funcionarioResult);

        return funcionario;
    }

    @Override
    public void delete(UUID id) {
        this.funcionarioRepository.deleteById(id);
    }
}
