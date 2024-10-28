package com.megalabsapi.api;

import com.megalabsapi.dto.*;
import com.megalabsapi.entity.LoginAttempt;
import com.megalabsapi.entity.Role;
import com.megalabsapi.entity.VerificationCode;
import com.megalabsapi.enums.ERole;
import com.megalabsapi.exception.RoleNotFoundException;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.repository.RoleRepository;
import com.megalabsapi.repository.VerificationCodeRepository;
import com.megalabsapi.security.TokenProvider;
import com.megalabsapi.service.LoginAttemptService;
import com.megalabsapi.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import com.megalabsapi.entity.Representante;
import com.megalabsapi.service.RepresentanteService;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RepresentanteService representanteService;
    @Autowired
    private RepresentanteRepository representanteRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private TokenProvider tokenProvider; // Reemplazamos JwtService con TokenProvider

    // Endpoint para iniciar sesión

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Representante representante = representanteService.autenticarUsuario(loginRequestDTO);

            // Crear un objeto Authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    representante.getDni(), null, representante.getRole().getAuthorities());

            // Generar el token usando Authentication
            String token = tokenProvider.createAccessToken(authentication);

            LoginResponseDTO responseDTO = new LoginResponseDTO("Inicio de sesión exitoso", representante.getNombre(), token);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Error: Datos inválidos", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO("Error inesperado: " + e.getMessage(), null, null));
        }
    }


    // Endpoint para registrar un nuevo representante
    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> register(@RequestBody UserRegistrationDTO registrationDTO) {
        try {
            Representante nuevoRepresentante = new Representante();
            nuevoRepresentante.setDni(registrationDTO.getDni());
            nuevoRepresentante.setEmail(registrationDTO.getEmail());
            nuevoRepresentante.setNombre(registrationDTO.getNombre());
            nuevoRepresentante.setSedeAsignada(registrationDTO.getSedeAsignada());

            // Convertir el roleName (String) a ERole y asignarlo al Representante
            ERole roleEnum = ERole.valueOf(registrationDTO.getRoleName());

            // Obtener el objeto Role desde la base de datos
            Role role = roleRepository.findByName(roleEnum)
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));

            nuevoRepresentante.setRole(role);

            representanteRepository.save(nuevoRepresentante);

            UserProfileDTO profileDTO = new UserProfileDTO(
                    nuevoRepresentante.getDni(),
                    nuevoRepresentante.getEmail(),
                    nuevoRepresentante.getNombre(),
                    nuevoRepresentante.getSedeAsignada(),
                    nuevoRepresentante.getRole().getName()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(profileDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Error por rol inválido
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody RecoverPasswordRequestDTO recoverPasswordRequest) {
        Representante representante = representanteRepository.findByEmail(recoverPasswordRequest.getEmail()).orElse(null);

        if (representante == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correo electrónico no encontrado");
        }

        String recoveryToken = UUID.randomUUID().toString();
        String encodedToken = URLEncoder.encode(recoveryToken, StandardCharsets.UTF_8);
        String recoveryUrl = "http://localhost:8080/recover-password?token=" + encodedToken;

        String message = String.format("Hola %s,\n\nHaga clic en el siguiente enlace para recuperar su contraseña:\n%s", representante.getNombre(), recoveryUrl);

        notificationService.sendRecoveryEmail(representante.getEmail(), message);

        return ResponseEntity.ok("Se ha enviado un enlace de recuperación a su correo electrónico");
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


    @GetMapping("/suspicious-activities")
    public ResponseEntity<List<LoginAttempt>> getSuspiciousActivities(@RequestParam String representanteDni) {
        List<LoginAttempt> suspiciousAttempts = loginAttemptService.getSuspiciousAttemptsByRepresentanteDni(representanteDni);

        if (suspiciousAttempts.isEmpty()) {
            return ResponseEntity.notFound().build();
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

        if (!tokenProvider.validateToken(token)) {  // Usamos TokenProvider para validar el token
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
