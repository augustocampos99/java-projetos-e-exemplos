package br.com.empresaweb.empresa_api.domain.repositories;

import br.com.empresaweb.empresa_api.domain.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    @Query("select f from Funcionario f where f.cpf = :cpf")
    public Funcionario findByCpf(@Param("cpf") String crm);

}
