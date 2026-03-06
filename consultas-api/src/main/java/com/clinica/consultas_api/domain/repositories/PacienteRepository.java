package com.clinica.consultas_api.domain.repositories;

import com.clinica.consultas_api.domain.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    @Query("select p from Paciente p where p.cpf = :cpf")
    public Paciente findByCpf(@Param("cpf") String cpf);

}
