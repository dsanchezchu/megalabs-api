package com.megalabsapi.api;

import com.megalabsapi.model.entity.Regulacion;
import com.megalabsapi.model.enums.AuditoriaStatus;
import com.megalabsapi.service.RegulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regulaciones")
public class RegulacionController {

    @Autowired
    private RegulacionService regulacionService;

    // Obtener todas las regulaciones
    @GetMapping
    public List<Regulacion> getAllRegulaciones() {
        return regulacionService.getAllRegulaciones();
    }

    // Crear una nueva regulación
    @PostMapping
    public Regulacion createRegulacion(@RequestBody Regulacion regulacion) {
        return regulacionService.saveRegulacion(regulacion);
    }

    // Actualizar el estado de auditoría de una regulación
    @PutMapping("/{id}")
    public Regulacion updateEstadoAuditoria(@PathVariable Integer id, @RequestParam AuditoriaStatus estado) {
        return regulacionService.updateEstadoAuditoria(id, estado);
    }
}