package com.megalabsapi.service;

import com.megalabsapi.model.entity.Cita;
import com.megalabsapi.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public Cita programarCita(String nombreCliente, String motivo, LocalDateTime fechaHora) {
        Cita cita = new Cita();
        cita.setNombreCliente(nombreCliente);
        cita.setMotivo(motivo);
        cita.setFechaHora(fechaHora);
        cita.setEstado("Pendiente");
        cita.setCancelada(false);

        return citaRepository.save(cita);
    }

    public Cita cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setCancelada(true);
        cita.setEstado("Inasistencia");
        return citaRepository.save(cita);
    }
}