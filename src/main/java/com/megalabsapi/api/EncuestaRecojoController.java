package com.megalabsapi.api;
import com.megalabsapi.model.entity.EncuestaRecojo;
import com.megalabsapi.service.EncuestaRecojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/encuestas-recojo")
public class EncuestaRecojoController {

    @Autowired
    private EncuestaRecojoService encuestaRecojoService;

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping
    public EncuestaRecojo crearEncuesta(@RequestBody EncuestaRecojo encuesta) {
        return encuestaRecojoService.guardarEncuesta(encuesta);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/reporte")
    public Map<String, Double> obtenerReporte() {
        return Map.of(
                "Promedio Puntualidad Recojo", encuestaRecojoService.calcularPromedio("puntualidadRecojo"),
                "Promedio Estado del Producto", encuestaRecojoService.calcularPromedio("estadoProducto"),
                "Promedio Amabilidad del Personal", encuestaRecojoService.calcularPromedio("amabilidadPersonal"),
                "Promedio Claridad de Instrucciones", encuestaRecojoService.calcularPromedio("claridadInstrucciones")
        );
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping
    public List<EncuestaRecojo> listarEncuestas() {
        return encuestaRecojoService.obtenerTodasEncuestas();
    }
}