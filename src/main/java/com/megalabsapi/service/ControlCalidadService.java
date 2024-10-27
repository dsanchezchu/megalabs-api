package com.megalabsapi.service;

import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.entity.Control_Calidad;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface ControlCalidadService {
    List<EstudioClinicoDTO> buscarPorProducto(String producto);

    List<EstudioClinicoDTO> buscarPorCliente(String cliente);

    List<EstudioClinicoDTO> buscarPorFecha(Date fechaInicio, Date fechaFin);
}