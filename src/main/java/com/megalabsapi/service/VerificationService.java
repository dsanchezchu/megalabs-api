package com.megalabsapi.model.service;

import com.megalabsapi.entity.Representante;
import com.megalabsapi.entity.VerificationCode;

public interface VerificationService {
    String generateVerificationCode();

    VerificationCode createVerificationCode(Representante representante);

    void sendVerificationCode(String email);
}

