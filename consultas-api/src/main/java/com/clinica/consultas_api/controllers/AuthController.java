package com.clinica.consultas_api.controllers;

import com.clinica.consultas_api.dtos.request.LoginRequestDto;
import com.clinica.consultas_api.dtos.response.TokenResponseDto;
import com.clinica.consultas_api.infra.security.SecurityJwtService;
import com.clinica.consultas_api.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/V1/auth")
@SecurityRequirement(name = "bearer-key")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityJwtService jwtService;

    private UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, SecurityJwtService jwtService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequest) {
        // Only for tests
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

        var authenticationRequestToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var verifyAuthentication = authenticationManager.authenticate(authenticationRequestToken);
        UserDetails user = usuarioService.loadUserByUsername(loginRequest.username());
        var token = jwtService.gerarToken(user.getUsername());

        return ResponseEntity.ok(new TokenResponseDto(token));
    }
}
