package com.megalabsapi.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;       // El token JWT
    private String nombre;      // El nombre completo del representante
    private String role;        // El rol del representante (e.g., ROLE_REPRESENTANTE)
}
