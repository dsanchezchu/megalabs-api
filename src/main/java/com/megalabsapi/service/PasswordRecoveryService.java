package com.megalabsapi.service;

import java.io.UnsupportedEncodingException;

/**
 * Servicio para gestionar la recuperación de contraseñas de los usuarios mediante la generación de tokens de recuperación.
 */
public interface PasswordRecoveryService {

    /**
     * Crea un token de recuperación de contraseña para el usuario identificado por su correo electrónico
     * y envía un enlace de recuperación a dicho correo.
     *
     * @param email El correo electrónico del usuario para el cual se generará el token de recuperación.
     * @throws UnsupportedEncodingException si ocurre un error al codificar el token en la URL de recuperación.
     */
    void createPasswordRecoveryToken(String email) throws UnsupportedEncodingException;
}

