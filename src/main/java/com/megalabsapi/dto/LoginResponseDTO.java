package com.megalabsapi.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String mensaje;
    private String nombre;
    private String token;

    public LoginResponseDTO(String mensaje, String nombre, String token) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.token = token;
    }


    public LoginResponseDTO(String s, Object o) {
    }

    public LoginResponseDTO() {

    }
}
