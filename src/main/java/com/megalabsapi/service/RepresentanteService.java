package com.megalabsapi.service;

import com.megalabsapi.dto.LoginRequestDTO;
import com.megalabsapi.model.entity.Representante;

/**
 * Servicio para gestionar las operaciones relacionadas con los representantes,
 * incluyendo registro, autenticación y actualización de credenciales.
 */
public interface RepresentanteService {

    /**
     * Registra un nuevo representante en el sistema con su contraseña encriptada.
     *
     * @param representante El representante a registrar.
     * @return El representante registrado.
     */
    Representante registrarRepresentante(Representante representante);

    /**
     * Autentica un representante usando su DNI y contraseña. Incrementa el contador de
     * intentos fallidos si la autenticación falla.
     *
     * @param loginRequestDTO Los datos de inicio de sesión del representante.
     * @return El representante autenticado.
     * @throws IllegalArgumentException si el DNI o la contraseña son incorrectos.
     */
    Representante autenticarUsuario(LoginRequestDTO loginRequestDTO);

    /**
     * Actualiza las credenciales de un representante, cambiando su contraseña y/o correo electrónico.
     * Verifica si el nuevo correo electrónico ya está en uso antes de actualizar.
     *
     * @param representante   El representante cuyas credenciales serán actualizadas.
     * @param nuevaContraseña La nueva contraseña en texto plano que será encriptada.
     * @param nuevoEmail      El nuevo correo electrónico a asignar al representante.
     * @throws IllegalArgumentException si el nuevo correo electrónico ya está en uso.
     */
    void actualizarCredenciales(Representante representante, String nuevaContraseña, String nuevoEmail);

    /**
     * Busca un representante en el sistema por su DNI.
     *
     * @param dni El DNI del representante.
     * @return El representante encontrado.
     * @throws IllegalArgumentException si el representante no se encuentra.
     */
    Representante findByDni(String dni);

    Representante save(Representante representante);
}
