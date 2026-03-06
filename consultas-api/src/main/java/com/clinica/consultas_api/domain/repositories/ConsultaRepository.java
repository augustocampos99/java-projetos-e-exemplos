package com.clinica.consultas_api.domain.repositories;

import com.clinica.consultas_api.domain.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
}
