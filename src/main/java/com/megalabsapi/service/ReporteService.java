package com.megalabsapi.service;

import com.megalabsapi.model.entity.Reporte;
import java.util.List;

public interface ReporteService<T extends Reporte> {
    T crearReporte(T reporte);
    T enviarReporte(Integer id);
    List<T> obtenerReportesEnviados();
    List<T> obtenerTodos();
}