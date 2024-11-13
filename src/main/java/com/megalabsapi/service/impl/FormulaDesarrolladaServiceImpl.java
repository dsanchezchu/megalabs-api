package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Formula_Desarrollada;
import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.repository.FormulaDesarrolladaRepository;
import com.megalabsapi.repository.ProductoRepository;
import com.megalabsapi.service.FormulaDesarrolladaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaDesarrolladaServiceImpl implements FormulaDesarrolladaService {

    @Autowired
    private FormulaDesarrolladaRepository formulaRepository;

    @Autowired
    private ProductoRepository productoRepository;  // Inyectamos el repositorio de Producto

    @Override
    public List<Formula_Desarrollada> buscarPorProducto(Producto producto) {
        return formulaRepository.findByProducto(producto);
    }

    @Override
    public String generarResumenComparativo(Formula_Desarrollada nueva, Formula_Desarrollada anterior) {
        return "Comparación entre " + nueva.getNombre() + " y " + anterior.getNombre() + ":\n"
                + "Beneficios nuevos: " + nueva.getBeneficios() + "\n"
                + "Diferencias: " + nueva.getDiferencias();
    }

    @Override
    public Optional<Formula_Desarrollada> buscarPorId(Integer id) {
        return formulaRepository.findById(id);
    }

    @Override
    public Optional<Producto> buscarProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId);  // Nuevo método para buscar producto
    }
}
