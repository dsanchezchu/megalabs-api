package com.megalabsapi.service;

import com.megalabsapi.dto.ActualizarEstudioClinicoDTO;
import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.dto.RegistroEstudioClinicoDTO;

import java.sql.Date;
import java.util.List;

public interface ControlCalidadService {
    List<EstudioClinicoDTO> buscarPorProducto(String producto);

    List<EstudioClinicoDTO> buscarPorCliente(String cliente);

    List<EstudioClinicoDTO> buscarPorFecha(Date fechaInicio, Date fechaFin);

    void registrarEstudioClinico(RegistroEstudioClinicoDTO registroDTO);

    void actualizarEstudioClinico(ActualizarEstudioClinicoDTO actualizarDTO);
}


