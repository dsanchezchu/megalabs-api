package com.megalabsapi.api;

import com.megalabsapi.model.entity.EstudioClinico;
import com.megalabsapi.service.EstudioClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios")
public class EstudioClinicoController {
    @Autowired
    private EstudioClinicoService estudioClinicoService;

    @GetMapping
    public ResponseEntity<List<EstudioClinico>> obtenerTodosLosEstudios() {
        List<EstudioClinico> estudios = estudioClinicoService.obtenerTodosLosEstudios();
        return ResponseEntity.ok(estudios);
    }

    @PostMapping
    public ResponseEntity<EstudioClinico> crearEstudio(@RequestBody EstudioClinico estudioClinico) {
        EstudioClinico nuevoEstudio = estudioClinicoService.guardarEstudio(estudioClinico);
        return ResponseEntity.status(201).body(nuevoEstudio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioClinico> obtenerEstudioPorId(@PathVariable Long id) {
        EstudioClinico estudio = estudioClinicoService.obtenerEstudioPorId(id);
        return estudio != null ? ResponseEntity.ok(estudio) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudio(@PathVariable Long id) {
        estudioClinicoService.eliminarEstudio(id);
        return ResponseEntity.noContent().build();
    }
}