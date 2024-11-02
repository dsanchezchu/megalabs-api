package com.megalabsapi.service;

import com.megalabsapi.model.entity.Formula_Desarrollada;
import com.megalabsapi.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface FormulaDesarrolladaService {
    List<Formula_Desarrollada> buscarPorProducto(Producto producto);  // Buscar fórmulas por producto
    String generarResumenComparativo(Formula_Desarrollada nueva, Formula_Desarrollada anterior);  // Comparar fórmulas
    Optional<Formula_Desarrollada> buscarPorId(Integer id);  // Buscar fórmula por ID
    Optional<Producto> buscarProductoPorId(Integer productoId);  // Nuevo método para buscar producto por ID
}
