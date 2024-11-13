package com.megalabsapi.service;

import com.megalabsapi.dto.AuditoriaDTO;


import java.util.List;

public interface AuditoriaService {
    void registrarAccion(String accion, String pantalla, String usuario);
    List<AuditoriaDTO> obtenerAuditorias();
}
