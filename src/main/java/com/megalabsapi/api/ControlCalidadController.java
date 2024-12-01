package com.megalabsapi.api;

import com.megalabsapi.dto.ActualizarEstudioClinicoDTO;
import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.dto.RegistroEstudioClinicoDTO;
import com.megalabsapi.service.ControlCalidadService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/control-calidad")
public class ControlCalidadController {

    @Autowired
    private ControlCalidadService controlCalidadService;


    // Búsqueda por criterios: producto, cliente (médico) y fecha
    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/buscar/estudios")
    public List<EstudioClinicoDTO> buscarEstudios(
            @RequestParam(value = "producto", required = false) String producto,
            @RequestParam(value = "cliente", required = false) String cliente,
            @RequestParam(value = "fechaInicio", required = false) Date fechaInicio,
            @RequestParam(value = "fechaFin", required = false) Date fechaFin) {

        if (producto != null) {
            return controlCalidadService.buscarPorProducto(producto);
        } else if (cliente != null) {
            return controlCalidadService.buscarPorCliente(cliente);
        } else if (fechaInicio != null && fechaFin != null) {
            return controlCalidadService.buscarPorFecha(fechaInicio, fechaFin);
        } else {
            return List.of(); // Lista vacía si no se pasan criterios
        }
    }

    // Endpoint para registrar estudios clínicos
    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/registrar/estudio-clinico")
    public ResponseEntity<String> registrarEstudioClinico(@RequestBody RegistroEstudioClinicoDTO registroDTO) {
        try {
            controlCalidadService.registrarEstudioClinico(registroDTO);
            return ResponseEntity.ok("Estudio clínico registrado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el estudio clínico: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarEstudioClinico(@RequestBody ActualizarEstudioClinicoDTO actualizarDTO) {
        try {
            controlCalidadService.actualizarEstudioClinico(actualizarDTO);
            return ResponseEntity.ok("Estudio clínico actualizado con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


