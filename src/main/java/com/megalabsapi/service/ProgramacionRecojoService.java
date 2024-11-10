package com.megalabsapi.service;

import com.megalabsapi.dto.ProgramacionRecojoDTO;

import java.util.List;

public interface ProgramacionRecojoService {
    List<ProgramacionRecojoDTO> obtenerProgramacionRecojo();
    void crearProgramacionRecojo(ProgramacionRecojoDTO programacionDTO);
    void confirmarRecojo(Long id);
    List<ProgramacionRecojoDTO> obtenerProgramacionPorRuc(String ruc);
}

