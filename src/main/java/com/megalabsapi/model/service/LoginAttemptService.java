package com.megalabsapi.model.service;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.Representante;

import java.util.List;

public interface LoginAttemptService {
    void recordLoginAttempt(String ipAddress, String device, String location, Representante representante);
    List<LoginAttempt> getSuspiciousAttemptsByRepresentante(Long representanteId);
}

