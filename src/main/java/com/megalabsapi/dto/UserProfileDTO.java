package com.megalabsapi.dto;

import com.megalabsapi.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserProfileDTO {

    private String dni;           // Identificador del representante
    private String email;         // Correo electrónico del representante
    private String nombre;        // Nombre completo del representante
    private String sedeAsignada;  // Sede asignada al representante
    private ERole role;           // El rol del representante (ROLE_REPRESENTANTE)
}
