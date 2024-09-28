package com.megalabsapi.api;

import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.service.ControlCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/control-calidad")
public class ControlCalidadController {

    @Autowired
    private ControlCalidadService controlCalidadService;

    @GetMapping
    public ResponseEntity<List<Control_Calidad>> listarControlesCalidad() {
        List<Control_Calidad> controles = controlCalidadService.listarControlesCalidad();
        return ResponseEntity.ok(controles);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Control_Calidad>> listarControlesPorProducto(@PathVariable Integer idProducto) {
        List<Control_Calidad> controles = controlCalidadService.buscarPorProducto(idProducto);
        return ResponseEntity.ok(controles);
    }

    @PostMapping
    public ResponseEntity<Control_Calidad> crearControlCalidad(@RequestBody Control_Calidad controlCalidad) {
        Control_Calidad nuevoControl = controlCalidadService.guardarControlCalidad(controlCalidad);
        return ResponseEntity.ok(nuevoControl);
    }

    @PutMapping("/{idControl}")
    public ResponseEntity<Control_Calidad> actualizarControlCalidad(@PathVariable Integer idControl, @RequestBody Control_Calidad controlCalidad) {
        Control_Calidad actualizado = controlCalidadService.actualizarControlCalidad(idControl, controlCalidad);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idControl}")
    public ResponseEntity<Void> eliminarControlCalidad(@PathVariable Integer idControl) {
        controlCalidadService.eliminarControlCalidad(idControl);
        return ResponseEntity.noContent().build();
    }
}