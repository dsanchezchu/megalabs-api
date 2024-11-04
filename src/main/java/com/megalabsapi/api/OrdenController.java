package com.megalabsapi.api;

import com.megalabsapi.model.entity.Orden;
import com.megalabsapi.model.enums.EstadoOrden;
import com.megalabsapi.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    @Autowired
    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        return ResponseEntity.ok(ordenService.crearOrden(orden));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Orden> actualizarEstado(@PathVariable Integer id, @RequestParam EstadoOrden estado) {
        return ResponseEntity.ok(ordenService.actualizarEstado(id, estado));
    }

    @PutMapping("/{id}/problema")
    public ResponseEntity<Orden> reportarProblema(@PathVariable Integer id, @RequestParam String problema) {
        return ResponseEntity.ok(ordenService.reportarProblema(id, problema));
    }
}

