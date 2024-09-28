package com.megalabsapi.api;

import com.megalabsapi.model.entity.Entrevista;
import com.megalabsapi.service.EntrevistaService; // Asegúrate de tener un servicio para manejar la lógica
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @GetMapping
    public ResponseEntity<List<Entrevista>> listarEntrevistas() {
        List<Entrevista> entrevistas = entrevistaService.obtenerTodasLasEntrevistas();
        return ResponseEntity.ok(entrevistas);
    }

    @PostMapping
    public ResponseEntity<Entrevista> crearEntrevista(@RequestBody Entrevista entrevista) {
        Entrevista nuevaEntrevista = entrevistaService.crearEntrevista(entrevista);
        return ResponseEntity.status(201).body(nuevaEntrevista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrevista> actualizarEntrevista(@PathVariable Integer id, @RequestBody Entrevista entrevista) {
        Entrevista entrevistaActualizada = entrevistaService.actualizarEntrevista(id, entrevista);
        return ResponseEntity.ok(entrevistaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrevista(@PathVariable Integer id) {
        entrevistaService.eliminarEntrevista(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/representante/{dniRepresentante}")
    public ResponseEntity<List<Entrevista>> obtenerEntrevistasPorRepresentante(@PathVariable String dniRepresentante) {
        List<Entrevista> entrevistas = entrevistaService.obtenerEntrevistasPorRepresentante(dniRepresentante);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Entrevista>> obtenerEntrevistasPorFecha(@PathVariable Date fecha) {
        List<Entrevista> entrevistas = entrevistaService.obtenerEntrevistasPorFecha(fecha);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/sede/{lugarSede}")
    public ResponseEntity<List<Entrevista>> obtenerEntrevistasPorSede(@PathVariable String lugarSede) {
        List<Entrevista> entrevistas = entrevistaService.obtenerEntrevistasPorSede(lugarSede);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/cliente/{rucCliente}")
    public ResponseEntity<List<Entrevista>> obtenerEntrevistasPorCliente(@PathVariable String rucCliente) {
        List<Entrevista> entrevistas = entrevistaService.obtenerEntrevistasPorCliente(rucCliente);
        return ResponseEntity.ok(entrevistas);
    }
}