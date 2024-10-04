package com.megalabsapi.api;

import com.megalabsapi.dto.EstudioClinicoDTO;
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

    // Búsqueda por criterios: producto, cliente (médico) y fecha
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
}