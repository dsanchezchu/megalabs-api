package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Formula_Desarrollada;
import com.megalabsapi.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormulaDesarrolladaRepository extends JpaRepository<Formula_Desarrollada, Integer> {
    List<Formula_Desarrollada> findByProducto(Producto producto);
}

