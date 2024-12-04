package com.megalabsapi.api;

import com.megalabsapi.dto.EntrevistaHisDTO;
import com.megalabsapi.service.EntrevistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @PostMapping
    public ResponseEntity<EntrevistaHisDTO> crearEntrevista(@RequestBody EntrevistaHisDTO dto) {
        EntrevistaHisDTO nuevaEntrevista = entrevistaService.crearEntrevista(dto);
        return ResponseEntity.ok(nuevaEntrevista);
    }

    @PutMapping("/{idEntrevista}")
    public ResponseEntity<EntrevistaHisDTO> actualizarEntrevista(
            @PathVariable Integer idEntrevista,
            @RequestBody EntrevistaHisDTO dto) {
        EntrevistaHisDTO entrevistaActualizada = entrevistaService.actualizarEntrevista(idEntrevista, dto);
        return ResponseEntity.ok(entrevistaActualizada);
    }

    @GetMapping
    public ResponseEntity<List<EntrevistaHisDTO>> obtenerTodasLasEntrevistas() {
        List<EntrevistaHisDTO> entrevistas = entrevistaService.obtenerTodasLasEntrevistas();
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/{idEntrevista}")
    public ResponseEntity<EntrevistaHisDTO> obtenerEntrevistaPorId(@PathVariable Integer idEntrevista) {
        return entrevistaService.obtenerTodasLasEntrevistas().stream()
                .filter(dto -> dto.getIdEntrevista().equals(idEntrevista))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/representante/{dniRepresentante}")
    public ResponseEntity<List<EntrevistaHisDTO>> obtenerEntrevistasPorRepresentante(@PathVariable String dniRepresentante) {
        List<EntrevistaHisDTO> entrevistas = entrevistaService.obtenerEntrevistasPorRepresentante(dniRepresentante);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<EntrevistaHisDTO>> obtenerEntrevistasPorFecha(@RequestParam Date fecha) {
        List<EntrevistaHisDTO> entrevistas = entrevistaService.obtenerEntrevistasPorFecha(fecha);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/sede")
    public ResponseEntity<List<EntrevistaHisDTO>> obtenerEntrevistasPorSede(@RequestParam String lugarSede) {
        List<EntrevistaHisDTO> entrevistas = entrevistaService.obtenerEntrevistasPorSede(lugarSede);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/cliente/{rucCliente}")
    public ResponseEntity<List<EntrevistaHisDTO>> obtenerEntrevistasPorCliente(@PathVariable String rucCliente) {
        List<EntrevistaHisDTO> entrevistas = entrevistaService.obtenerEntrevistasPorCliente(rucCliente);
        return ResponseEntity.ok(entrevistas);
    }

    @DeleteMapping("/{idEntrevista}")
    public ResponseEntity<Void> eliminarEntrevista(@PathVariable Integer idEntrevista) {
        entrevistaService.eliminarEntrevista(idEntrevista);
        return ResponseEntity.noContent().build();
    }
}
