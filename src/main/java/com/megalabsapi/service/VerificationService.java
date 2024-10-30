package com.megalabsapi.service;

import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.entity.VerificationCode;

/**
 * Servicio para gestionar la generación y envío de códigos de verificación para representantes.
 */
public interface VerificationService {

    /**
     * Genera un código de verificación aleatorio de 6 dígitos.
     *
     * @return Un código de verificación de 6 dígitos como cadena de texto.
     */
    String generateVerificationCode();

    /**
     * Crea un nuevo código de verificación para el representante especificado, con una fecha de expiración configurada.
     *
     * @param representante El representante para el cual se generará el código de verificación.
     * @return La entidad VerificationCode generada y guardada en la base de datos.
     */
    VerificationCode createVerificationCode(Representante representante);

    /**
     * Envía un código de verificación al correo electrónico especificado. Si existe un código no utilizado
     * y no expirado, se reutiliza ese código; de lo contrario, se genera uno nuevo.
     *
     * @param email El correo electrónico al cual se enviará el código de verificación.
     * @throws IllegalArgumentException si no se encuentra un representante con el correo electrónico proporcionado.
     */
    void sendVerificationCode(String email);
}


