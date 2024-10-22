package com.megalabsapi.service;

import com.megalabsapi.entity.LoginAttempt;
import com.megalabsapi.entity.Representante;

import java.util.List;

public interface LoginAttemptService {
    void recordLoginAttempt(String ipAddress, String device, String location, Representante representante);
    List<LoginAttempt> getSuspiciousAttemptsByRepresentanteDni(String dni);
}

