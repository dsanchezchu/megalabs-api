package com.megalabsapi.service;

public interface NotificationService {
    void sendRecoveryEmail(String email, String recoveryUrl);
}

