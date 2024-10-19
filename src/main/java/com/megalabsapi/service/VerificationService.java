package com.megalabsapi.model.service;

import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.entity.VerificationCode;

public interface VerificationService {
    String generateVerificationCode();

    VerificationCode createVerificationCode(Representante representante);

    void sendVerificationCode(String email);
}

