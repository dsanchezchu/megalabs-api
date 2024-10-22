package com.megalabsapi.service;

import com.megalabsapi.dto.LoginRequestDTO;
import com.megalabsapi.entity.Representante;

public interface RepresentanteService {
    Representante registrarRepresentante(Representante representante);

    Representante autenticarUsuario(LoginRequestDTO loginRequestDTO);

    void actualizarCredenciales(Representante representante, String nuevaContraseña, String nuevoEmail);

    Representante findByDni(String dni);
}
