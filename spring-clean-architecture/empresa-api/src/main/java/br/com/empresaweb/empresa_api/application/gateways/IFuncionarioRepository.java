package br.com.empresaweb.empresa_api.application.gateways;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;

import java.util.List;
import java.util.UUID;

public interface IFuncionarioRepository {
    public List<Funcionario> findAll();
    public Funcionario findById(UUID id);
    public Funcionario findByCpf(String cpf);
    public Funcionario create(Funcionario funcionario);
    public Funcionario update(UUID id, Funcionario funcionario);
    public void delete(UUID id);
}
