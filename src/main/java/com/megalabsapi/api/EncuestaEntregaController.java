package com.megalabsapi.api;
import com.megalabsapi.model.entity.EncuestaEntrega;
import com.megalabsapi.service.EncuestaEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/encuestas-entrega")
public class EncuestaEntregaController {

    @Autowired
    private EncuestaEntregaService encuestaEntregaService;

    @PostMapping
    public EncuestaEntrega crearEncuesta(@RequestBody EncuestaEntrega encuesta) {
        return encuestaEntregaService.guardarEncuesta(encuesta);
    }

    @GetMapping("/reporte")
    public Map<String, Double> obtenerReporte() {
        return Map.of(
                "Promedio Puntualidad de Entrega", encuestaEntregaService.calcularPromedio("puntualidadEntrega"),
                "Promedio Estado del Producto", encuestaEntregaService.calcularPromedio("estadoProducto"),
                "Promedio Profesionalismo del Personal", encuestaEntregaService.calcularPromedio("profesionalismoPersonal"),
                "Promedio Facilidad de Contacto", encuestaEntregaService.calcularPromedio("facilidadContacto")
        );
    }

    @GetMapping
    public List<EncuestaEntrega> listarEncuestas() {
        return encuestaEntregaService.obtenerTodasEncuestas();
    }
}