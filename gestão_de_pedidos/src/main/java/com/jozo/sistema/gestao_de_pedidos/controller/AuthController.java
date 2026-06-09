package com.jozo.sistema.gestao_de_pedidos.controller;

import com.jozo.sistema.gestao_de_pedidos.dto.LoginRequest;
import com.jozo.sistema.gestao_de_pedidos.dto.LoginResponse;
import com.jozo.sistema.gestao_de_pedidos.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        if(!request.username().equals("admin") || !request.password().equals("admin123")) {
            throw new RuntimeException("Credenciais invalidas");
        }
        String token = jwtService.generateToken(request.username());

        return new LoginResponse(token);
    }
}
