package com.megalabsapi.model.service;

import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;

public interface RepresentanteService {
    Representante registrarRepresentante(Representante representante);

    LoginResponseDTO autenticarUsuario(LoginRequestDTO loginRequestDTO);
}
