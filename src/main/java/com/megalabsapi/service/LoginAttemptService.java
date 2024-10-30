package com.megalabsapi.service;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.Representante;

import java.util.List;

/**
 * Servicio para gestionar los intentos de inicio de sesión de representantes,
 * incluyendo la detección y registro de actividades sospechosas.
 */
public interface LoginAttemptService {

    /**
     * Registra un intento de inicio de sesión para un representante, almacenando
     * información sobre la dirección IP, el dispositivo y la ubicación.
     * Si el intento se considera sospechoso, se envía una notificación.
     *
     * @param ipAddress     La dirección IP desde donde se realiza el intento.
     * @param device        El dispositivo utilizado para el intento de inicio de sesión.
     * @param location      La ubicación geográfica del intento de inicio de sesión.
     * @param representante El representante que intenta iniciar sesión.
     */
    void recordLoginAttempt(String ipAddress, String device, String location, Representante representante);

    /**
     * Obtiene una lista de intentos de inicio de sesión sospechosos asociados a un representante
     * identificado por su DNI.
     *
     * @param dni El DNI del representante.
     * @return Lista de intentos de inicio de sesión sospechosos.
     * @throws IllegalArgumentException si no se encuentra un representante con el DNI proporcionado.
     */
    List<LoginAttempt> getSuspiciousAttemptsByRepresentanteDni(String dni) throws IllegalArgumentException;
}



