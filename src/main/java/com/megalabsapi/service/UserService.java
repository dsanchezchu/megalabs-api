package com.megalabsapi.service;

import com.megalabsapi.dto.AuthResponseDTO;
import com.megalabsapi.dto.LoginDTO;
import com.megalabsapi.dto.UserProfileDTO;
import com.megalabsapi.dto.UserRegistrationDTO;

/**
 * Servicio para gestionar las operaciones de usuario para representantes, incluyendo
 * el registro, la autenticación y la gestión de perfiles.
 */
public interface UserService {

    /**
     * Registra un nuevo representante en el sistema.
     *
     * @param registrationDTO Los datos de registro del representante.
     * @return Un DTO con el perfil del representante registrado.
     * @throws BadRequestException si el DNI ya está registrado en el sistema.
     */
    UserProfileDTO registerRepresentante(UserRegistrationDTO registrationDTO);

    /**
     * Autentica un representante usando su DNI y contraseña, generando un token JWT para el acceso.
     *
     * @param loginDTO Los datos de inicio de sesión del representante.
     * @return Un DTO de respuesta de autenticación que incluye el token JWT.
     * @throws UsernameNotFoundException si no se encuentra un representante con el DNI proporcionado.
     * @throws InvalidCredentialsException si la contraseña es incorrecta.
     */
    AuthResponseDTO login(LoginDTO loginDTO);

    /**
     * Actualiza el perfil de un representante, incluyendo el nombre, el email y la sede asignada.
     *
     * @param dni            El DNI del representante a actualizar.
     * @param userProfileDTO Los datos actualizados del perfil del representante.
     * @return Un DTO con el perfil actualizado del representante.
     * @throws ResourceNotFoundException si no se encuentra un representante con el DNI proporcionado.
     * @throws BadRequestException si el nuevo email ya está en uso por otro representante.
     */
    UserProfileDTO updateUserProfile(String dni, UserProfileDTO userProfileDTO);

    /**
     * Obtiene el perfil de un representante usando su DNI.
     *
     * @param dni El DNI del representante.
     * @return Un DTO con el perfil del representante encontrado.
     * @throws ResourceNotFoundException si no se encuentra un representante con el DNI proporcionado.
     */
    UserProfileDTO getUserProfileByDni(String dni);
}
