package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Control_Calidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ControlCalidadRepository extends JpaRepository<Control_Calidad, Integer> {
    List<Control_Calidad> findByProductoIdProducto(Integer idProducto);

}