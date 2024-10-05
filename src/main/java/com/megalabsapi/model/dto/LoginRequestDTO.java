package com.megalabsapi.model.dto;


import lombok.Data;

@Data
public class LoginRequestDTO {
    private String dni;
    private String email;
    private String password;
}
