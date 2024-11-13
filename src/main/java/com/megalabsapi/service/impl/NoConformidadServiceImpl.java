package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.entity.NoConformidad;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.repository.NoConformidadRepository;
import com.megalabsapi.service.NoConformidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoConformidadServiceImpl implements NoConformidadService {

    @Autowired
    private NoConformidadRepository noConformidadRepository;

    @Autowired
    private EntregaMuestraRepository entregaMuestraRepository;

    @Override
    public List<NoConformidad> obtenerNoConformidadesPorEntrega(Entrega_Muestra entregaMuestra) {
        return noConformidadRepository.findByEntregaMuestra(entregaMuestra);  // Cambiado a entidad completa
    }

    @Override
    public List<NoConformidad> obtenerNoConformidadesPorCliente(String ruc) {
        List<Entrega_Muestra> entregas = entregaMuestraRepository.findByClienteRuc(ruc);  // Buscar entregas por cliente
        return entregas.stream()
                .flatMap(entrega -> noConformidadRepository.findByEntregaMuestra(entrega).stream())  // Obtener no conformidades por cada entrega
                .toList();
    }

    @Override
    public Optional<NoConformidad> buscarPorId(Integer id) {
        return noConformidadRepository.findById(id);
    }

    @Override
    public NoConformidad crearNoConformidad(NoConformidad noConformidad) {
        return noConformidadRepository.save(noConformidad);
    }

    @Override
    public List<NoConformidad> obtenerNoConformidadesCriticas() {
        return noConformidadRepository.findByCritico(true);
    }

    @Override
    public void enviarNotificacionCritica(NoConformidad noConformidad) {
        // Implementación de notificación
    }

    @Override
    public String generarReporteCliente(NoConformidad noConformidad) {
        return "Reporte de No Conformidad:\n" +
                "Descripción: " + noConformidad.getDescripcionProblema() + "\n" +
                "Fecha de Detección: " + noConformidad.getFechaDeteccion() + "\n" +
                "Causa: " + noConformidad.getCausaRaiz() + "\n" +
                "Acciones Correctivas: " + noConformidad.getAccionesCorrectivas() + "\n" +
                "Resultado del Seguimiento: " + noConformidad.getResultadoSeguimiento();
    }
}
