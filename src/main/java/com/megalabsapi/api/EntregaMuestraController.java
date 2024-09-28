package com.megalabsapi.api;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Entrega_Muestra>> obtenerEntregasPorCliente(@PathVariable String ruc) {
        List<Entrega_Muestra> entregas = entregaMuestraService.obtenerEntregasPorCliente(ruc);
        return ResponseEntity.ok(entregas);
    }
}