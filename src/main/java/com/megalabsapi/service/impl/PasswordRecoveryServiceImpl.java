package com.megalabsapi.service.impl;

import com.megalabsapi.integration.notification.email.dto.Mail;
import com.megalabsapi.integration.notification.email.service.EmailService;
import com.megalabsapi.model.entity.PasswordRecoveryToken;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.PasswordRecoveryTokenRepository;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.PasswordRecoveryService;
import com.megalabsapi.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    private final RepresentanteRepository representanteRepository;
    private final PasswordRecoveryTokenRepository tokenRepository;
    private final NotificationService notificationService;
    private final TemplateEngine templateEngine;
    private final EmailService emailService; // Asegúrate de que sea final para la inyección de dependencias

    @Value("${megalabas-api.openapi.dev-url}")
    private String baseUrl;

    @Value("${token.expiration.time}")
    private long tokenExpirationTime;  // En segundos, configurable en properties

    // Inyectamos EmailService a través del constructor
    public PasswordRecoveryServiceImpl(RepresentanteRepository representanteRepository,
                                       PasswordRecoveryTokenRepository tokenRepository,
                                       NotificationService notificationService,
                                       TemplateEngine templateEngine,
                                       EmailService emailService) {
        this.representanteRepository = representanteRepository;
        this.tokenRepository = tokenRepository;
        this.notificationService = notificationService;
        this.templateEngine = templateEngine;
        this.emailService = emailService; // Se inyecta correctamente
    }

    @Override
    public void createPasswordRecoveryToken(String email) {
        // Buscar al representante por correo electrónico
        Representante representante = representanteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Correo electrónico no encontrado"));

        // Generar el token único
        String token = UUID.randomUUID().toString();

        // Crear y configurar el objeto `PasswordRecoveryToken`
        PasswordRecoveryToken recoveryToken = new PasswordRecoveryToken();
        recoveryToken.setToken(token);
        recoveryToken.setRepresentante(representante);
        recoveryToken.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(tokenExpirationTime)));

        // Guardar el token en la base de datos
        tokenRepository.save(recoveryToken);

        // Preparar el modelo con los datos para el correo
        Map<String, Object> model = new HashMap<>();
        model.put("nombreUsuario", representante.getNombre());
        model.put("codigoRecuperacion", token); // Incluimos solo el token

        // Crear el objeto Mail con los datos
        Mail mail = emailService.createMail(representante.getEmail(), "Código de Recuperación de Contraseña", model, "no-reply@megalabs.com");

        // Enviar el correo utilizando el template de Thymeleaf
        try {
            emailService.sendEmail(mail, "password-reset-templates.html"); // Asegúrate de usar la plantilla correcta
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo de recuperación", e);
        }
    }

}
