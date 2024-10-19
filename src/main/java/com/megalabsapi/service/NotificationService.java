package com.megalabsapi.model.service;

public interface NotificationService {
    void sendRecoveryEmail(String email, String recoveryUrl);
}

