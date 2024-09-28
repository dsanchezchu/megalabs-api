package com.megalabsapi.model.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String mensaje;
    private String nombre;

    public LoginResponseDTO(String s, Object o) {
    }

    public LoginResponseDTO() {

    }
}
