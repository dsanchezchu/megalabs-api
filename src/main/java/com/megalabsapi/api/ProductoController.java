package com.megalabsapi.api;

import com.megalabsapi.dto.ProductoDTO;
import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.service.CatalogoService;
import com.megalabsapi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/productos/desabastecidos")
    public List<Producto> verProductosEnDesabastecimiento() {
        return productoService.obtenerProductosEnDesabastecimiento();
    }

    @GetMapping("/productos/todos")
    public List<ProductoDTO> obtenerInventarioCatalogo() {
        return catalogoService.obtenerProductosEnCatalogo().stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        return new ProductoDTO(
                producto.getIdProducto(),
                producto.getFechaVenta(),
                producto.getNombre(),
                producto.getStock()
        );
    }
}

