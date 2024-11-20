package com.megalabsapi.api;

import com.megalabsapi.dto.CitaDTO;
import com.megalabsapi.dto.CitaRequestDTO;
import com.megalabsapi.model.entity.Cita;
import com.megalabsapi.model.enums.CitaMotivo;
import com.megalabsapi.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Programar cita
    @PostMapping("/programar")
    public ResponseEntity<CitaDTO> programarCita(@RequestBody CitaRequestDTO citaRequestDTO) {
        Cita cita = citaService.programarCita(
                citaRequestDTO.getNombreCliente(),
                citaRequestDTO.getMotivo(),
                citaRequestDTO.getFechaHora(),
                citaRequestDTO.getDniRepresentante()
        );

        CitaDTO citaDTO = new CitaDTO(
                cita.getId(),
                cita.getCliente().getNombre(),
                cita.getCliente().getRuc(),
                cita.getRepresentante().getNombre(),
                cita.getRepresentante().getDni(),
                cita.getMotivo(),
                cita.getFechaHora(),
                cita.getEstado(),
                cita.isCancelada()
        );

        return ResponseEntity.ok(citaDTO);
    }


    // Cancelar cita
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<CitaDTO> cancelarCita(@PathVariable Long id) {
        Cita cita = citaService.cancelarCita(id);

        CitaDTO citaDTO = new CitaDTO(
                cita.getId(),
                cita.getCliente().getNombre(),
                cita.getCliente().getRuc(),
                cita.getRepresentante().getNombre(),
                cita.getRepresentante().getDni(),
                cita.getMotivo(),
                cita.getFechaHora(),
                cita.getEstado(),
                cita.isCancelada()
        );

        return ResponseEntity.ok(citaDTO);
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<CitaDTO> actualizarCita(
            @PathVariable Long id,
            @Valid @RequestBody CitaRequestDTO citaRequestDTO) {

        // Construir el objeto Cita actualizado
        Cita citaActualizada = new Cita();
        if (citaRequestDTO.getMotivo() != null) {
            citaActualizada.setMotivo(citaRequestDTO.getMotivo());
        }
        if (citaRequestDTO.getFechaHora() != null) {
            citaActualizada.setFechaHora(citaRequestDTO.getFechaHora());
        }
        if (citaRequestDTO.getEstado() != null) { // Agrega esta validación
            citaActualizada.setEstado(citaRequestDTO.getEstado());
        }

        Cita cita = citaService.actualizarCita(id, citaActualizada);

        // Crear el DTO asegurando manejar datos nulos
        CitaDTO citaDTO = new CitaDTO(
                cita.getId(),
                cita.getCliente() != null ? cita.getCliente().getNombre() : null,
                cita.getCliente() != null ? cita.getCliente().getRuc() : null,
                cita.getRepresentante() != null ? cita.getRepresentante().getNombre() : null,
                cita.getRepresentante() != null ? cita.getRepresentante().getDni() : null,
                cita.getMotivo(),
                cita.getFechaHora(),
                cita.getEstado(),
                cita.isCancelada()
        );

        return ResponseEntity.ok(citaDTO);
    }



    // Obtener citas por representante
    @GetMapping("/representante/{dniRepresentante}")
    public ResponseEntity<List<CitaDTO>> obtenerCitasPorRepresentante(@PathVariable String dniRepresentante) {
        List<Cita> citas = citaService.obtenerCitasPorRepresentante(dniRepresentante);

        List<CitaDTO> citaDTOs = citas.stream()
                .map(cita -> new CitaDTO(
                        cita.getId(),
                        cita.getCliente().getNombre(),
                        cita.getCliente().getRuc(),
                        cita.getRepresentante().getNombre(),
                        cita.getRepresentante().getDni(),
                        cita.getMotivo(),
                        cita.getFechaHora(),
                        cita.getEstado(),
                        cita.isCancelada()
                ))
                .toList();

        return ResponseEntity.ok(citaDTOs);
    }
}
