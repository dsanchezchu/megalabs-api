package com.megalabsapi.api;

import com.megalabsapi.model.entity.InsatisfaccionProducto;
import com.megalabsapi.model.enums.TipoQueja;
import com.megalabsapi.service.InsatisfaccionProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/insatisfaccion-producto")
public class InsatisfaccionProductoController {

    @Autowired
    private InsatisfaccionProductoService insatisfaccionProductoService;

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping
    public InsatisfaccionProducto crearInsatisfaccion(@RequestBody InsatisfaccionProducto insatisfaccion) {
        return insatisfaccionProductoService.guardarInsatisfaccion(insatisfaccion);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/reporte")
    public Map<String, List<InsatisfaccionProducto>> obtenerReporte() {
        return Map.of(
                "Quejas de Calidad", insatisfaccionProductoService.obtenerPorTipoQueja(TipoQueja.CALIDAD),
                "Quejas de Entrega", insatisfaccionProductoService.obtenerPorTipoQueja(TipoQueja.ENTREGA),
                "Quejas de Funcionalidad", insatisfaccionProductoService.obtenerPorTipoQueja(TipoQueja.FUNCIONALIDAD)
        );
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/distribuir-reporte")
    public void distribuirReporte() {
        insatisfaccionProductoService.distribuirReporte();
    }
}