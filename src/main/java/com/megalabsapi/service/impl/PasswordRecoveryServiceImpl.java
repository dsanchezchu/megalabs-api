package com.megalabsapi.model.service.impl;

import com.megalabsapi.model.entity.PasswordRecoveryToken;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.mapper.RecoverPasswordMapper;
import com.megalabsapi.model.repository.PasswordRecoveryTokenRepository;
import com.megalabsapi.model.repository.RepresentanteRepository;
import com.megalabsapi.model.service.PasswordRecoveryService;
import com.megalabsapi.model.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private PasswordRecoveryTokenRepository tokenRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RecoverPasswordMapper recoverPasswordMapper;  // Inyectamos el mapper

    // Inyectamos la URL base desde las propiedades del sistema
    @Value("${app.base-url}")
    private String baseUrl;

    @Override
    public void createPasswordRecoveryToken(String email) throws UnsupportedEncodingException {
        // Buscar al representante por correo electrónico
        Representante representante = representanteRepository.findByEmail(email);
        if (representante == null) {
            throw new RuntimeException("Correo electrónico no encontrado");
        }

        // Generar el token único
        String token = UUID.randomUUID().toString();

        // Crear el objeto `PasswordRecoveryToken`
        PasswordRecoveryToken recoveryToken = new PasswordRecoveryToken();
        recoveryToken.setToken(token);
        recoveryToken.setRepresentante(representante);
        recoveryToken.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(3600))); // 1 hora de expiración

        // Guardar el token en la base de datos
        tokenRepository.save(recoveryToken);

        // Codificar el token para la URL
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8.toString());
        String recoveryUrl = baseUrl + "/recover-password?token=" + encodedToken;

        // Crear el mensaje con el nombre del usuario y el enlace de recuperación
        String message = String.format(
                "Hola %s,\n\nHaga clic en el siguiente enlace para recuperar su contraseña:\n%s",
                representante.getNombre(), recoveryUrl);  // Aquí se asegura que el enlace y el nombre del usuario están bien concatenados.

        // Enviar el correo de recuperación
        notificationService.sendRecoveryEmail(representante.getEmail(), message);
    }
}
