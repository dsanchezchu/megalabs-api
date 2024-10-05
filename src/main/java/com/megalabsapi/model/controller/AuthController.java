package com.megalabsapi.model.controller;


import com.megalabsapi.model.dto.*;
import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.VerificationCode;
import com.megalabsapi.model.repository.RepresentanteRepository;
import com.megalabsapi.model.repository.VerificationCodeRepository;
import com.megalabsapi.model.service.JwtService;
import com.megalabsapi.model.service.LoginAttemptService;
import com.megalabsapi.model.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.service.RepresentanteService;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
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

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private JwtService jwtService;


    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Representante representante = representanteService.autenticarUsuario(loginRequestDTO);
            String token = jwtService.generateToken(representante.getEmail());
            LoginResponseDTO responseDTO = new LoginResponseDTO("Inicio de sesión exitoso", representante.getNombre(), token);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO("Error: Datos inválidos", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO("Error inesperado: " + e.getMessage(), null, null));
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
        Representante representante = representanteRepository.findByEmail(recoverPasswordRequest.getEmail());

        if (representante == null) {
            return ResponseEntity.badRequest().body("Correo electrónico no encontrado");
        }

        // Generar token de recuperación
        String recoveryToken = UUID.randomUUID().toString();

        // Crear la URL de recuperación
        String encodedToken = URLEncoder.encode(recoveryToken, StandardCharsets.UTF_8);
        String recoveryUrl = "http://localhost:8080/recover-password?token=" + encodedToken;  // Cambia la URL base según corresponda.

        // Crear el mensaje de recuperación de contraseña con el nombre del representante y el enlace
        String message = String.format("Hola %s,\n\nHaga clic en el siguiente enlace para recuperar su contraseña:\n%s",
                representante.getNombre(), recoveryUrl);

        // Enviar correo de recuperación usando el email almacenado
        notificationService.sendRecoveryEmail(representante.getEmail(), message);

        return ResponseEntity.ok("Se ha enviado un enlace de recuperación a su correo electrónico");
    }


    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeRequestDTO verifyCodeRequest) {
        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndRepresentanteDni(
                verifyCodeRequest.getCode(), verifyCodeRequest.getRepresentanteDni());

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

    @GetMapping("/suspicious-activities")
    public ResponseEntity<List<LoginAttempt>> getSuspiciousActivities(@RequestParam String representanteDni) {
        // Llamamos al servicio usando el DNI del representante en lugar del ID
        List<LoginAttempt> suspiciousAttempts = loginAttemptService.getSuspiciousAttemptsByRepresentanteDni(representanteDni);

        if (suspiciousAttempts.isEmpty()) {
            return ResponseEntity.notFound().build();  // Devolvemos un 404 si no hay actividades sospechosas
        }

        return ResponseEntity.ok(suspiciousAttempts);
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @PostMapping("/actualizar-credenciales")
    public ResponseEntity<String> actualizarCredenciales(
            @RequestHeader("Authorization") String token,
            @RequestBody ActualizarCredencialesRequestDTO request) {

        if (!jwtService.isTokenValid(token)) {  // isTokenValid mejor que validateToken
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }

        Representante representante = representanteService.findByDni(request.getDni());
        if (representante == null) {
            return ResponseEntity.badRequest().body("Representante no encontrado");
        }

        representanteService.actualizarCredenciales(representante, request.getNuevaContraseña(), request.getNuevoEmail());

        return ResponseEntity.ok("Credenciales actualizadas exitosamente");
    }

}

