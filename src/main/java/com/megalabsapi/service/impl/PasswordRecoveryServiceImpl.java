package com.megalabsapi.service.impl;

import com.megalabsapi.entity.PasswordRecoveryToken;
import com.megalabsapi.entity.Representante;
import com.megalabsapi.mapper.RecoverPasswordMapper;
import com.megalabsapi.repository.PasswordRecoveryTokenRepository;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.PasswordRecoveryService;
import com.megalabsapi.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    private final RepresentanteRepository representanteRepository;
    private final PasswordRecoveryTokenRepository tokenRepository;
    private final NotificationService notificationService;
    private final RecoverPasswordMapper recoverPasswordMapper;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${token.expiration.time}")
    private long tokenExpirationTime;  // En segundos, configurable en properties

    public PasswordRecoveryServiceImpl(RepresentanteRepository representanteRepository,
                                       PasswordRecoveryTokenRepository tokenRepository,
                                       NotificationService notificationService,
                                       RecoverPasswordMapper recoverPasswordMapper) {
        this.representanteRepository = representanteRepository;
        this.tokenRepository = tokenRepository;
        this.notificationService = notificationService;
        this.recoverPasswordMapper = recoverPasswordMapper;
    }

    @Override
    public void createPasswordRecoveryToken(String email) throws UnsupportedEncodingException {
        // Buscar al representante por correo electrónico usando Optional para manejar el caso de no encontrado
        Representante representante = representanteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Correo electrónico no encontrado"));

        // Generar el token único
        String token = UUID.randomUUID().toString();

        // Crear y configurar el objeto `PasswordRecoveryToken`
        PasswordRecoveryToken recoveryToken = new PasswordRecoveryToken();
        recoveryToken.setToken(token);
        recoveryToken.setRepresentante(representante);
        recoveryToken.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(tokenExpirationTime))); // Token configurable

        // Guardar el token en la base de datos
        tokenRepository.save(recoveryToken);

        // Codificar el token para la URL de recuperación
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8.toString());
        String recoveryUrl = baseUrl + "/recover-password?token=" + encodedToken;

        // Crear el mensaje con el enlace de recuperación
        String message = String.format(
                "Hola %s,\n\nHaga clic en el siguiente enlace para recuperar su contraseña:\n%s",
                representante.getNombre(), recoveryUrl);

        // Enviar el correo de recuperación
        notificationService.sendRecoveryEmail(representante.getEmail(), message);
    }
}
