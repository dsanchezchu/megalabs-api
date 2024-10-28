package com.megalabsapi.service;

/**
 * Servicio de notificación para enviar correos electrónicos a los usuarios.
 */
public interface NotificationService {

    /**
     * Envía un correo electrónico con el asunto y el cuerpo proporcionados al destinatario especificado.
     *
     * @param email   El correo electrónico del destinatario.
     * @param subject El asunto del correo.
     * @param message El cuerpo del mensaje del correo.
     */
    void sendEmail(String email, String subject, String message);

    /**
     * Envía un correo de recuperación de contraseña con el enlace de recuperación proporcionado.
     *
     * @param email       El correo electrónico del destinatario.
     * @param recoveryUrl El enlace de recuperación de contraseña.
     */
    void sendRecoveryEmail(String email, String recoveryUrl);
}


