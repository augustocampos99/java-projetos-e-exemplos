package com.clinica.consultas_api.domain.repositories;

import com.clinica.consultas_api.domain.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Informa ao spring que é testes de pesistencia
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Necessário para não procurar banco em memória
@ActiveProfiles("test") // Necessário para utilizar o profile de testes e apontar para o banco de testes
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar um usuário da base de dados")
    void findByUsernameCenario1Test() {
        var nome = "José Augusto";
        var username = "jose.campos@gmail.com";
        var password = "123456";
        var role = "ADMIN";


        var result = this.criarUsuario(nome, username, password, role);

        assertNotNull(result);;
        assertEquals(nome, result.getNome());
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
        assertEquals(role, result.getRole());
    }

    @Test
    @DisplayName("Deve retornar usuario null")
    void findByUsernameCenario2Test() {
        var result = usuarioRepository.findByUsername("ana.campos@gmail.com");
        // assertThat(result).isNull();
        assertNull(result);
    }

    private Usuario criarUsuario(String nome, String username, String password, String role) {
        var usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setNome(nome);
        usuario.setPassword(password);
        usuario.setRole(role);
        return this.usuarioRepository.save(usuario);
    }
}