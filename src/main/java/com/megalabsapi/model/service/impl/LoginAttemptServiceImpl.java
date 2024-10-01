package com.megalabsapi.model.service.impl;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.repository.LoginAttemptRepository;
import com.megalabsapi.model.service.NotificationService;
import com.megalabsapi.model.service.LoginAttemptService;
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
    private NotificationService notificationService;

    @Override
    public void recordLoginAttempt(String ipAddress, String device, String location, Representante representante) {
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setIpAddress(ipAddress);
        loginAttempt.setDevice(device);
        loginAttempt.setLocation(location);
        loginAttempt.setAttemptTime(Timestamp.from(Instant.now()));
        loginAttempt.setRepresentante(representante);

        // Verifica si la actividad es sospechosa
        boolean isSuspicious = isSuspiciousAttempt(ipAddress, device, representante);
        loginAttempt.setIsSuspicious(isSuspicious);

        loginAttemptRepository.save(loginAttempt);

        // Si la actividad es sospechosa, envía una notificación al usuario
        if (isSuspicious) {
            String message = String.format("Actividad sospechosa detectada: \n" +
                    "Dispositivo: %s\n" +
                    "IP: %s\n" +
                    "Ubicación: %s\n" +
                    "Fecha/Hora: %s", device, ipAddress, location, loginAttempt.getAttemptTime().toString());

            notificationService.sendRecoveryEmail(representante.getEmail(), message);
        }
    }

    @Override
    public List<LoginAttempt> getSuspiciousAttemptsByRepresentante(Long representanteId) {
        return List.of();
    }

    private boolean isSuspiciousAttempt(String ipAddress, String device, Representante representante) {
        LoginAttempt lastAttempt = loginAttemptRepository.findTopByRepresentanteOrderByAttemptTimeDesc(representante);

        if (lastAttempt != null) {
            return !lastAttempt.getIpAddress().equals(ipAddress) || !lastAttempt.getDevice().equals(device);
        }

        // Si no hay intentos anteriores, no se considera sospechoso
        return false;
    }
}
