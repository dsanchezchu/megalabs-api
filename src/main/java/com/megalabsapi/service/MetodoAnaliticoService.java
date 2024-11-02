package com.megalabsapi.service;

import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.model.entity.MetodoAnalitico;

import java.util.List;
import java.util.Optional;

public interface MetodoAnaliticoService {
    List<MetodoAnalitico> obtenerTodosMetodos();
    Optional<MetodoAnalitico> obtenerMetodoPorId(Integer id);
    List<MetodoAnalitico> obtenerMetodosPorControlCalidad(Control_Calidad controlCalidad);  // Nuevo método
    MetodoAnalitico crearMetodoAnalitico(MetodoAnalitico metodoAnalitico);
    String generarResumenMetodo(MetodoAnalitico metodoAnalitico);
}