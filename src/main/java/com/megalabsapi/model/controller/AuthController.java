package com.megalabsapi.model.controller;

import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.service.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RepresentanteService representanteService;

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // Llamamos al servicio para autenticar al usuario
            LoginResponseDTO responseDTO = representanteService.autenticarUsuario(loginRequestDTO);
            return ResponseEntity.ok(responseDTO);  // Enviamos respuesta de éxito con los detalles del usuario
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO("Error: " + e.getMessage(), null));
        }
    }

    // Endpoint para registrar un nuevo representante
    @PostMapping("/register")
    public ResponseEntity<Representante> register(@RequestBody Representante representante) {
        try {
            // Registramos el representante cifrando su contraseña
            Representante nuevoRepresentante = representanteService.registrarRepresentante(representante);
            return ResponseEntity.ok(nuevoRepresentante);  // Enviamos respuesta con el representante creado
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // En caso de error, enviamos una respuesta vacía
        }
    }
}

