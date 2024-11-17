package com.megalabsapi.service;

import jakarta.mail.MessagingException;

public interface NotificacionClienteService {
    void sendMailCliente(String to, String message) throws MessagingException;
}
