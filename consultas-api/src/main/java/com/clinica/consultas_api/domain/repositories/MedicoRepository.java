package com.clinica.consultas_api.domain.repositories;

import com.clinica.consultas_api.domain.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    @Query("select m from Medico m where m.crm = :crm")
    public Medico findByCpf(@Param("crm") String crm);

}
