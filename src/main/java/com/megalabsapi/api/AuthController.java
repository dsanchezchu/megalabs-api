package com.megalabsapi.api;

import com.megalabsapi.dto.*;
import com.megalabsapi.exception.ResourceNotFoundException;
import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.PasswordRecoveryToken;
import com.megalabsapi.model.entity.VerificationCode;
import com.megalabsapi.repository.PasswordRecoveryTokenRepository;
import com.megalabsapi.repository.VerificationCodeRepository;
import com.megalabsapi.security.TokenProvider;
import com.megalabsapi.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import com.megalabsapi.model.entity.Representante;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RepresentanteService representanteService;
    @Autowired
    private PasswordRecoveryTokenRepository tokenRepository;
    @Autowired
    private PasswordRecoveryService passwordRecoveryService;
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private TokenProvider tokenProvider;

    // Endpoint para iniciar sesión

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            AuthResponseDTO authResponse = userService.login(loginDTO);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Datos inválidos");
        }
    }



    // Endpoint para registrar un nuevo representante
    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> register(@RequestBody UserRegistrationDTO registrationDTO) {
        try {
            UserProfileDTO profileDTO = userService.registerRepresentante(registrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(profileDTO);
        } catch (DataIntegrityViolationException e) {
            UserProfileDTO errorDTO = new UserProfileDTO();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
        }
    }



    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody RecoverPasswordRequestDTO recoverPasswordRequest) {
        try {
            // Delegar la lógica al servicio de recuperación de contraseñas
            passwordRecoveryService.createPasswordRecoveryToken(recoverPasswordRequest.getEmail());
            return ResponseEntity.ok("Se ha enviado un enlace de recuperación a su correo electrónico");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud");
        }
    }

    @GetMapping("/verify-recovery-token")
    public ResponseEntity<?> verifyRecoveryToken(@RequestParam String token) {
        Optional<PasswordRecoveryToken> optionalToken = tokenRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Token inválido o no encontrado.");
        }

        PasswordRecoveryToken recoveryToken = optionalToken.get();

        if (recoveryToken.getExpiryDate().before(Timestamp.from(Instant.now()))) {
            return ResponseEntity.badRequest().body("El token ha expirado.");
        }

        if (recoveryToken.isUsed()) {
            return ResponseEntity.badRequest().body("El token ya fue utilizado.");
        }

        recoveryToken.setUsed(true);
        tokenRepository.save(recoveryToken);

        // Crear un objeto de autenticación simulado
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                recoveryToken.getRepresentante().getDni(),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_REPRESENTANTE"))
        );

        // Generar un nuevo token JWT
        String newToken = tokenProvider.createAccessToken(authentication);

        // Crear el objeto AuthResponseDTO con los datos requeridos
        String nombre = recoveryToken.getRepresentante().getNombre();
        String role = "ROLE_REPRESENTANTE";
        AuthResponseDTO response = new AuthResponseDTO(newToken, nombre, role);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyCodeRequestDTO verifyCodeRequest) {
        Optional<VerificationCode> optionalVerificationCode =
                verificationCodeRepository.findByCodeAndRepresentanteDni(
                        verifyCodeRequest.getCode(),
                        verifyCodeRequest.getRepresentanteDni()
                );

        if (optionalVerificationCode.isEmpty() || optionalVerificationCode.get().isUsed()) {
            return ResponseEntity.badRequest().body("Código inválido o ya utilizado");
        }

        VerificationCode verificationCode = optionalVerificationCode.get();
        if (verificationCode.getExpiryDate().before(new Timestamp(System.currentTimeMillis()))) {
            return ResponseEntity.badRequest().body("El código ha expirado");
        }

        verificationCode.setIsUsed(true);
        verificationCodeRepository.save(verificationCode);

        return ResponseEntity.ok("Verificación exitosa. Bienvenido al sistema");
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/suspicious-activities")
    public ResponseEntity<List<LoginAttempt>> getSuspiciousActivities(@RequestParam String representanteDni) {
        List<LoginAttempt> suspiciousAttempts = loginAttemptService.getSuspiciousAttemptsByRepresentanteDni(representanteDni);

        if (suspiciousAttempts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(suspiciousAttempts);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }


    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/update-credentials")
    public ResponseEntity<String> updateCredentials(@RequestBody ActualizarCredencialesRequestDTO updateCredentialsRequest) {
        // Buscar al representante por DNI
        Representante representante = representanteService.findByDni(updateCredentialsRequest.getDni());
        if (representante == null) {
            throw new ResourceNotFoundException("No se encontró un representante con ese DNI");
        }

        // Actualizar credenciales usando el nuevo correo proporcionado
        representanteService.actualizarCredenciales(
                representante,
                updateCredentialsRequest.getNuevaContraseña(),
                updateCredentialsRequest.getNuevoEmail()
        );

        return ResponseEntity.ok("Credenciales actualizadas exitosamente.");
    }

}
