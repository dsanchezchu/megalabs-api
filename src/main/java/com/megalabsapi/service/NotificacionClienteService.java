package com.megalabsapi.service;

import jakarta.mail.MessagingException;

public interface NotificacionClienteService {
    void sendMailRepresentante(String to, String message) throws MessagingException;
}
