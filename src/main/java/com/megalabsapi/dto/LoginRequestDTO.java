package com.megalabsapi.dto;


import lombok.Data;

@Data
public class LoginRequestDTO {
    private String dni;
    private String password;
}
