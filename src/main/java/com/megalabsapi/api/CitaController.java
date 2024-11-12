package com.megalabsapi.api;

import com.megalabsapi.model.entity.Cita;
import com.megalabsapi.service.CitaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/programar")
    public ResponseEntity<Cita> programarCita(@RequestParam String nombreCliente,
                                              @RequestParam String motivo,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        Cita cita = citaService.programarCita(nombreCliente, motivo, fechaHora);

        return ResponseEntity.ok(cita);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable Long id) {
        Cita cita = citaService.cancelarCita(id);
        return ResponseEntity.ok(cita);
    }
}