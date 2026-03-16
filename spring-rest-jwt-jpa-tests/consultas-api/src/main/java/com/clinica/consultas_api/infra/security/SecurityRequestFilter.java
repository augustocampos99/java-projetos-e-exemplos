package com.clinica.consultas_api.infra.security;

import com.clinica.consultas_api.domain.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityRequestFilter extends OncePerRequestFilter {

    private final SecurityJwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UserDetailsService userDetailsService;

    public SecurityRequestFilter(SecurityJwtService jwtService, UsuarioRepository usuarioRepository, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Validando JWT
        String token = getToken(request);
        if(token != null) {
            var subject = this.jwtService.validTokenGetSubject(token);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(subject);
            var authToken  = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken );
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token != null) {
            return token.replace("Bearer ", "");
        }

        return null;
    }
}
