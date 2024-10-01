package com.megalabsapi.model.controller;


import com.megalabsapi.model.dto.RecoverPasswordRequestDTO;
import com.megalabsapi.model.dto.VerifyCodeRequestDTO;
import com.megalabsapi.model.entity.VerificationCode;
import com.megalabsapi.model.repository.RepresentanteRepository;
import com.megalabsapi.model.repository.VerificationCodeRepository;
import com.megalabsapi.model.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.service.RepresentanteService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RepresentanteService representanteService;

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;


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

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody RecoverPasswordRequestDTO recoverPasswordRequest) {
        // Aquí se extrae el email para ser utilizado
        String email = recoverPasswordRequest.getEmail();
        // Continuar con la lógica de recuperación de contraseña
        Representante representante = representanteRepository.findByEmail(recoverPasswordRequest.getEmail());
        if (representante == null) {
            return ResponseEntity.badRequest().body("Correo electrónico no encontrado");
        }

        String recoveryToken = UUID.randomUUID().toString();
        // Lógica para guardar el token y enviarlo por email al usuario
        notificationService.sendRecoveryEmail(representante.getEmail(), recoveryToken);

        return ResponseEntity.ok("Se ha enviado un enlace de recuperación a su correo electrónico");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeRequestDTO verifyCodeRequest) {
        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndRepresentante(verifyCodeRequest.getCode(), verifyCodeRequest.getRepresentanteId());

        if (verificationCode == null || verificationCode.isUsed()) {
            return ResponseEntity.badRequest().body("Código inválido o ya utilizado");
        }

        if (verificationCode.getExpiryDate().before(new Timestamp(System.currentTimeMillis()))) {
            return ResponseEntity.badRequest().body("El código ha expirado");
        }

        verificationCode.setIsUsed(true);
        verificationCodeRepository.save(verificationCode);

        return ResponseEntity.ok("Verificación exitosa. Bienvenido al sistema");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

}

