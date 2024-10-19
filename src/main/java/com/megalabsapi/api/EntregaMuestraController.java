package com.megalabsapi.api;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega-muestra")
public class EntregaMuestraController {

    private final EntregaMuestraService entregaMuestraService;

    public EntregaMuestraController(EntregaMuestraService entregaMuestraService) {
        this.entregaMuestraService = entregaMuestraService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<List<EntregaMuestraDTO>> obtenerEntregasPorCliente(@PathVariable String ruc) {
        List<EntregaMuestraDTO> entregas = entregaMuestraService.obtenerEntregasPorCliente(ruc);
        return ResponseEntity.ok(entregas);
    }
}