package com.megalabsapi.model.service.impl;

import com.megalabsapi.entity.Representante;
import com.megalabsapi.entity.VerificationCode;
import com.megalabsapi.mapper.VerificationCodeMapper;
import com.megalabsapi.repository.VerificationCodeRepository;
import com.megalabsapi.model.service.NotificationService;
import com.megalabsapi.model.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;  // Inyectamos el mapper

    @Override
    public String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // Generar un número de 6 dígitos
        return String.valueOf(code);
    }

    @Override
    public VerificationCode createVerificationCode(Representante representante) {
        String code = generateVerificationCode();  // Genera un nuevo código de verificación
        Timestamp expiryDate = Timestamp.from(Instant.now().plusSeconds(300)); // Establecer la expiración a 5 minutos

        // Crear una nueva instancia de VerificationCode
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setExpiryDate(expiryDate);
        verificationCode.setRepresentante(representante);
        verificationCode.setIsUsed(false);

        // Guardar y retornar el código generado
        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public void sendVerificationCode(String email) {
        String verificationCode = generateVerificationCode();
        String message = "Su código de verificación es: " + verificationCode;
        notificationService.sendRecoveryEmail(email, message);
    }
}
