package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Formula_Desarrollada;
import com.megalabsapi.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaDesarrolladaRepository extends JpaRepository<Formula_Desarrollada, Integer> {
    List<Formula_Desarrollada> findByProducto(Producto producto);
}

