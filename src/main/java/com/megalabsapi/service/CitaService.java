package com.megalabsapi.service;

import com.megalabsapi.model.entity.Cita;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.enums.CitaMotivo;
import com.megalabsapi.model.enums.EstadoCita;
import com.megalabsapi.repository.CitaRepository;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final NotificationService notificationService;
    private final RepresentanteRepository representanteRepository;

    public CitaService(CitaRepository citaRepository,
                       ClienteRepository clienteRepository,
                       RepresentanteRepository representanteRepository, NotificationService notificationService) {
        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
        this.representanteRepository = representanteRepository;
        this.notificationService = notificationService;
    }

    // Programar cita
    public Cita programarCita(String nombreCliente, CitaMotivo motivo, LocalDateTime fechaHora, String dniRepresentante) {
        // Buscar cliente por nombre
        Cliente cliente = clienteRepository.findByNombre(nombreCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + nombreCliente));

        // Buscar representante por DNI
        Representante representante = representanteRepository.findById(dniRepresentante)
                .orElseThrow(() -> new RuntimeException("Representante no encontrado"));

        // Verificar conflicto de horario
        boolean existeConflicto = citaRepository.existsByClienteAndFechaHora(cliente, fechaHora) ||
                citaRepository.existsByRepresentanteAndFechaHora(representante, fechaHora);

        if (existeConflicto) {
            throw new RuntimeException("Ya existe una cita programada para este horario.");
        }

        // Crear la cita
        Cita cita = new Cita();
        cita.setCliente(cliente); // Relación con Cliente
        cita.setMotivo(motivo);
        cita.setFechaHora(fechaHora);
        cita.setEstado(EstadoCita.PENDIENTE);
        cita.setCancelada(false);
        cita.setRepresentante(representante);

        Cita citaGuardada = citaRepository.save(cita);

        // Enviar notificación al cliente
        String mensaje = String.format(
                "Hola %s,\n\nSe ha programado una cita con motivo: %s para la fecha y hora: %s.\n\nGracias.",
                cliente.getNombre(), motivo.name().replace("_", " "), fechaHora.toString()
        );
        notificationService.sendEmail(cliente.getEmail(), "Cita Programada", mensaje);

        return citaGuardada;
    }



    // Cancelar cita
    public Cita cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setCancelada(true);
        cita.setEstado(EstadoCita.INASISTENCIA);

        Cita citaCancelada = citaRepository.save(cita);

        // Enviar notificación al cliente
        Cliente cliente = cita.getCliente();
        String mensaje = String.format(
                "Hola %s,\n\nLamentamos informarte que la cita programada para la fecha y hora: %s ha sido cancelada.\n\nGracias.",
                cliente.getNombre(), cita.getFechaHora().toString()
        );
        notificationService.sendEmail(cliente.getEmail(), "Cita Cancelada", mensaje);

        return citaCancelada;
    }


    // Actualizar cita
    @Transactional
    public Cita actualizarCita(Long id, Cita citaActualizada) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        if (citaActualizada.getCliente() != null) {
            cita.setCliente(citaActualizada.getCliente());
        }
        if (citaActualizada.getMotivo() != null) {
            cita.setMotivo(citaActualizada.getMotivo());
        }
        if (citaActualizada.getFechaHora() != null) {
            cita.setFechaHora(citaActualizada.getFechaHora());
        }
        if (citaActualizada.getEstado() != null) { // Agrega esta validación
            cita.setEstado(citaActualizada.getEstado());
        }

        return citaRepository.save(cita);
    }




    // Obtener citas por representante
    public List<Cita> obtenerCitasPorRepresentante(String dniRepresentante) {
        Representante representante = representanteRepository.findById(dniRepresentante)
                .orElseThrow(() -> new RuntimeException("Representante no encontrado"));
        return citaRepository.findByRepresentante(representante);
    }
}
