package com.megalabsapi.service;

import java.sql.Date;
import java.util.List;

import com.megalabsapi.dto.EntrevistaHisDTO;

public interface EntrevistaService {
    List<EntrevistaHisDTO> obtenerEntrevistasPorRepresentante(String dniRepresentante);
    EntrevistaHisDTO crearEntrevista(EntrevistaHisDTO entrevista);
    EntrevistaHisDTO actualizarEntrevista(Integer idEntrevista, EntrevistaHisDTO entrevista);
    void eliminarEntrevista(Integer idEntrevista);
    List<EntrevistaHisDTO> obtenerTodasLasEntrevistas();
    List<EntrevistaHisDTO> obtenerEntrevistasPorFecha(Date fecha);
    List<EntrevistaHisDTO> obtenerEntrevistasPorSede(String lugarSede);
    List<EntrevistaHisDTO> obtenerEntrevistasPorCliente(String rucCliente);

}
