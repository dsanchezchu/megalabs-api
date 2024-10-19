package com.megalabsapi.security;

import java.security.SecureRandom;

public class GenerationCode {
    public String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // Generar un número de 6 dígitos
        return String.valueOf(code);
    }
}
