package com.clinica.consultas_api.domain.repositories;

import com.clinica.consultas_api.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("select u from Usuario u where u.username = :username")
    public Usuario findByUsername(@Param("username") String username);

}
