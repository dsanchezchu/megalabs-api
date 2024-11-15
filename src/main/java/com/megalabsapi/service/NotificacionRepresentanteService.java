package com.megalabsapi.service;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface NotificacionRepresentanteService {
    void sendMailRepresentante(String to, String message) throws MessagingException;
}
