package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.LoginAttemptRepository;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.NotificationService;
import com.megalabsapi.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void recordLoginAttempt(String ipAddress, String device, String location, Representante representante) {
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setIpAddress(ipAddress);
        loginAttempt.setDevice(device);
        loginAttempt.setLocation(location);
        loginAttempt.setAttemptTime(Timestamp.from(Instant.now()));
        loginAttempt.setRepresentante(representante);

        // Verificar si la actividad es sospechosa
        boolean isSuspicious = isSuspiciousAttempt(ipAddress, device, representante);
        loginAttempt.setIsSuspicious(isSuspicious);

        loginAttemptRepository.save(loginAttempt);

        // Si la actividad es sospechosa, envía una notificación al usuario
        if (isSuspicious) {
            String message = String.format("Actividad sospechosa detectada: \n" +
                    "Dispositivo: %s\n" +
                    "IP: %s\n" +
                    "Ubicación: %s\n" +
                    "Fecha/Hora: %s", device, ipAddress, location, loginAttempt.getAttemptTime());
            notificationService.sendRecoveryEmail(representante.getEmail(), message);
        }
    }

    @Override
    public List<LoginAttempt> getSuspiciousAttemptsByRepresentanteDni(String dni) {
        // Buscar el representante por DNI usando Optional para evitar NullPointerException
        Representante representante = representanteRepository.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException("Representante no encontrado con DNI: " + dni));

        // Buscar y devolver los intentos sospechosos asociados al representante
        return loginAttemptRepository.findByRepresentanteAndIsSuspiciousTrue(representante);
    }

    private boolean isSuspiciousAttempt(String ipAddress, String device, Representante representante) {
        return loginAttemptRepository.findTopByRepresentanteOrderByAttemptTimeDesc(representante)
                .map(lastAttempt -> !lastAttempt.getIpAddress().equals(ipAddress) || !lastAttempt.getDevice().equals(device))
                .orElse(false);
    }
}
