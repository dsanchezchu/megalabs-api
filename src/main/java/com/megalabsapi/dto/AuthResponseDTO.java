package com.megalabsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponseDTO {

    private String token;       // El token JWT
    private String nombre;      // El nombre completo del representante
    private String role;        // El rol del representante (e.g., ROLE_REPRESENTANTE)
}
