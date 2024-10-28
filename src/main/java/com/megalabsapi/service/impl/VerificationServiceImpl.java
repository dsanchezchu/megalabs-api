package com.megalabsapi.service.impl;

import com.megalabsapi.entity.Representante;
import com.megalabsapi.entity.VerificationCode;
import com.megalabsapi.mapper.VerificationCodeMapper;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.repository.VerificationCodeRepository;
import com.megalabsapi.service.NotificationService;
import com.megalabsapi.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Value("${verification.code.expiration-time:300}")
    private long codeExpirationTime; // Expiración en segundos, configurable

    @Override
    public String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // Generar un número de 6 dígitos
        return String.valueOf(code);
    }

    @Override
    public VerificationCode createVerificationCode(Representante representante) {
        String code = generateVerificationCode();
        Timestamp expiryDate = Timestamp.from(Instant.now().plusSeconds(codeExpirationTime));

        VerificationCode verificationCode = verificationCodeMapper.toVerificationCode(code, expiryDate, representante);

        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public void sendVerificationCode(String email) {
        Optional<VerificationCode> existingCode = verificationCodeRepository.findByRepresentanteEmailAndIsUsedFalse(email);

        String verificationCode;
        if (existingCode.isPresent() && existingCode.get().getExpiryDate().after(Timestamp.from(Instant.now()))) {
            verificationCode = existingCode.get().getCode();
        } else {
            Representante representante = representanteRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Representante no encontrado con el email: " + email));
            verificationCode = createVerificationCode(representante).getCode();
        }

        String message = "Su código de verificación es: " + verificationCode;
        notificationService.sendRecoveryEmail(email, message);
    }
}
