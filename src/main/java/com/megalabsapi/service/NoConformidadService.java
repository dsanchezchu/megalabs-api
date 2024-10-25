package com.megalabsapi.service;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.entity.NoConformidad;

import java.util.List;
import java.util.Optional;

public interface NoConformidadService {
    List<NoConformidad> obtenerNoConformidadesPorEntrega(Entrega_Muestra entregaMuestra);  // Buscar por entidad
    List<NoConformidad> obtenerNoConformidadesPorCliente(String ruc);
    Optional<NoConformidad> buscarPorId(Integer id);
    NoConformidad crearNoConformidad(NoConformidad noConformidad);
    List<NoConformidad> obtenerNoConformidadesCriticas();
    void enviarNotificacionCritica(NoConformidad noConformidad);
    String generarReporteCliente(NoConformidad noConformidad);
}
