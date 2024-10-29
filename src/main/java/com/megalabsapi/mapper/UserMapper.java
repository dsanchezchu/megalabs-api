package com.megalabsapi.mapper;

import com.megalabsapi.dto.AuthResponseDTO;
import com.megalabsapi.dto.LoginDTO;
import com.megalabsapi.dto.UserProfileDTO;
import com.megalabsapi.dto.UserRegistrationDTO;
import com.megalabsapi.entity.Representante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convertir de UserRegistrationDTO a Representante
    public Representante toRepresentanteEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, Representante.class);
    }

    // Convertir de Representante a UserProfileDTO para la respuesta
    public UserProfileDTO toUserProfileDTO(Representante representante) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setDni(representante.getDni());
        userProfileDTO.setEmail(representante.getEmail());
        userProfileDTO.setNombre(representante.getNombre());
        userProfileDTO.setLaboratorio(representante.getLaboratorio()); // Adaptar según los campos de UserProfileDTO
        userProfileDTO.setRole(representante.getRole().getName()); // Asumiendo que UserProfileDTO tiene un campo `role`
        return userProfileDTO;
    }

    // Convertir de LoginDTO a Representante (para el proceso de login)
    public Representante toRepresentanteEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Representante.class);
    }

    // Convertir de Representante a AuthResponseDTO para la respuesta de autenticación
    public AuthResponseDTO toAuthResponseDTO(Representante representante, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);  // Asignar el token

        // Asignar datos del representante
        authResponseDTO.setNombre(representante.getNombre());

        // Asignar el rol de representante
        authResponseDTO.setRole(representante.getRole().getName().toString());

        return authResponseDTO;
    }
}


