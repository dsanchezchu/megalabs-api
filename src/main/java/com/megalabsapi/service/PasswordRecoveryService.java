package com.megalabsapi.service;

import java.io.UnsupportedEncodingException;

public interface PasswordRecoveryService {
    void createPasswordRecoveryToken(String email) throws UnsupportedEncodingException;
}
