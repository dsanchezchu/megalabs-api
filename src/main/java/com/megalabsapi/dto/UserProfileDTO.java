package com.megalabsapi.dto;

import com.megalabsapi.entity.Laboratorio;
import com.megalabsapi.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDTO {

    private String dni;           // Identificador del representante
    private String email;         // Correo electrónico del representante
    private String nombre;        // Nombre completo del representante
    private Laboratorio laboratorio;  // Ruc del laboratorio asignado al representante
    private ERole role;           // El rol del representante (ROLE_REPRESENTANTE)
}
