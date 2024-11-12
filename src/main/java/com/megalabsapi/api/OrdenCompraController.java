package com.megalabsapi.api;

import com.megalabsapi.dto.OrdenCompraDTO;
import com.megalabsapi.model.entity.OrdenCompra;
import com.megalabsapi.service.OrdenCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
    private final OrdenCompraService ordenCompraService;

    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    @PostMapping
    public ResponseEntity<OrdenCompra> crearOrdenCompra(@RequestBody OrdenCompraDTO ordenCompraDTO) {
        OrdenCompra ordenCompra = ordenCompraService.crearOrdenCompra(
                ordenCompraDTO.getProveedorId(),
                ordenCompraDTO.getNombreMateriaPrima(),
                ordenCompraDTO.getCantidad(),
                ordenCompraDTO.getCostoTotal(),
                ordenCompraDTO.getTiempoEntrega()
        );
        return ResponseEntity.ok(ordenCompra);
    }
}