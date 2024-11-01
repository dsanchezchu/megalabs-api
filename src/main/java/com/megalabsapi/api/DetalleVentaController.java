package com.megalabsapi.api;

import com.megalabsapi.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/ventas")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;
    @GetMapping("/reporte")
    public ResponseEntity<List<Object[]>> obtenerReporteVentas() {
        List<Object[]> reporte = detalleVentaService.obtenerReporteVentas();
        return ResponseEntity.ok(reporte);
    }
}