package com.megalabsapi.api;

import com.megalabsapi.model.entity.Formula_Desarrollada;
import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.repository.ProductoRepository;
import com.megalabsapi.service.FormulaDesarrolladaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private FormulaDesarrolladaService formulaService;

    @GetMapping("/consultaPorProducto")
    public List<Formula_Desarrollada> consultarFormulasPorProducto(@RequestParam Integer productoId) {
        Optional<Producto> producto = formulaService.buscarProductoPorId(productoId);  // Buscar el producto por ID
        if (producto.isPresent()) {
            return formulaService.buscarPorProducto(producto.get());  // Pasar el producto completo al servicio
        } else {
            throw new RuntimeException("Producto no encontrado");  // Manejar error si el producto no se encuentra
        }
    }

    @GetMapping("/comparar")
    public String compararFormulas(@RequestParam Integer idNueva, @RequestParam Integer idAnterior) {
        Optional<Formula_Desarrollada> nueva = formulaService.buscarPorId(idNueva);
        Optional<Formula_Desarrollada> anterior = formulaService.buscarPorId(idAnterior);

        if (nueva.isPresent() && anterior.isPresent()) {
            return formulaService.generarResumenComparativo(nueva.get(), anterior.get());
        } else {
            return "Una o ambas fórmulas no se encontraron.";
        }
    }
}
