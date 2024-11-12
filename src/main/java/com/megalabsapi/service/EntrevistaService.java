package com.megalabsapi.service;

import java.sql.Date;
import java.util.List;
import com.megalabsapi.model.entity.Entrevista;

public interface EntrevistaService {
    List<Entrevista> obtenerEntrevistasPorRepresentante(String dniRepresentante);
    Entrevista crearEntrevista(Entrevista entrevista);
    Entrevista actualizarEntrevista(Integer idEntrevista, Entrevista entrevista);
    void eliminarEntrevista(Integer idEntrevista);
    List<Entrevista> obtenerTodasLasEntrevistas();
    List<Entrevista> obtenerEntrevistasPorFecha(Date fecha);
    List<Entrevista> obtenerEntrevistasPorSede(String lugarSede);
    List<Entrevista> obtenerEntrevistasPorCliente(String rucCliente);

}
