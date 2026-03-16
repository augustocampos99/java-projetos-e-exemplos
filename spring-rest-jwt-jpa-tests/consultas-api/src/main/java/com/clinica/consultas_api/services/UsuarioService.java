package com.clinica.consultas_api.services;

import com.clinica.consultas_api.domain.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository userRepository;

    public UsuarioService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userResult = this.userRepository.findByUsername(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(userResult.getUsername())
                .password(userResult.getPassword())
                .roles(userResult.getRole())
                .build();
    }
}
