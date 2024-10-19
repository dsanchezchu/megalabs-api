package com.megalabsapi.model.dto;

import lombok.Data;

@Data
public class ActualizarCredencialesRequestDTO {
    private String dni;
    private String nuevaContraseña;
    private String nuevoEmail;
}
