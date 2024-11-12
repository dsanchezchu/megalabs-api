package com.megalabsapi.api;

import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos/desabastecidos")
    public List<Producto> verProductosEnDesabastecimiento() {
        return productoService.obtenerProductosEnDesabastecimiento();
    }
}

