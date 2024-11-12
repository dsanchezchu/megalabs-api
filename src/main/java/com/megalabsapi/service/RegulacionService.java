package com.megalabsapi.service;

import com.megalabsapi.model.entity.Regulacion;
import com.megalabsapi.model.enums.AuditoriaStatus;
import com.megalabsapi.repository.RegulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegulacionService {

    @Autowired
    private RegulacionRepository regulacionRepository;

    // Obtener todas las regulaciones
    public List<Regulacion> getAllRegulaciones() {
        return regulacionRepository.findAll();
    }

    // Guardar una nueva regulación
    public Regulacion saveRegulacion(Regulacion regulacion) {
        return regulacionRepository.save(regulacion);
    }

    // Actualizar el estado de auditoría de una regulación existente
    public Regulacion updateEstadoAuditoria(Integer id, AuditoriaStatus estado) {
        Optional<Regulacion> regulacionOpt = regulacionRepository.findById(id);
        if (regulacionOpt.isPresent()) {
            Regulacion regulacion = regulacionOpt.get();
            regulacion.setEstadoAuditoria(estado);
            return regulacionRepository.save(regulacion);
        }
        throw new RuntimeException("Regulación no encontrada con ID: " + id);
    }
}