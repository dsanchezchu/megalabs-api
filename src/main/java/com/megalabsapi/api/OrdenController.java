package com.megalabsapi.api;

import com.megalabsapi.model.entity.Orden;
import com.megalabsapi.model.enums.EstadoOrden;
import com.megalabsapi.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    @Autowired
    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/crear")
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        return ResponseEntity.ok(ordenService.crearOrden(orden));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Orden> actualizarEstado(@PathVariable Integer id, @RequestParam EstadoOrden estado) {
        return ResponseEntity.ok(ordenService.actualizarEstado(id, estado));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{id}/problema")
    public ResponseEntity<Orden> reportarProblema(@PathVariable Integer id, @RequestParam String problema) {
        return ResponseEntity.ok(ordenService.reportarProblema(id, problema));
    }
}

