package br.com.empresaweb.empresa_api.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {

    @Query("select f from FuncionarioEntity f where f.cpf = :cpf FETCH FIRST 1 ROWS ONLY")
    public FuncionarioEntity findByCpf(@Param("cpf") String crm);

}
