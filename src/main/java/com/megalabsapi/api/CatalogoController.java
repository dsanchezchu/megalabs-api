package com.megalabsapi.api;

import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CatalogoController {

    private final CatalogoService catalogoService;

    @Autowired
    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/catalogo/productos")
    public List<Producto> obtenerInventarioCatalogo() {
        return catalogoService.obtenerProductosEnCatalogo();
    }
}

