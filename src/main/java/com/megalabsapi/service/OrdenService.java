package com.megalabsapi.service;

import com.megalabsapi.model.entity.Orden;
import com.megalabsapi.model.enums.EstadoOrden;

public interface OrdenService {
    Orden crearOrden(Orden orden);
    Orden actualizarEstado(Integer id, EstadoOrden estado);
    Orden reportarProblema(Integer id, String problema);
}