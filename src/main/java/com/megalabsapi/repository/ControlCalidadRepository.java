package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Control_Calidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ControlCalidadRepository extends JpaRepository<Control_Calidad, Integer> {
    // Búsqueda por nombre del producto
    @Query("SELECT c FROM Control_Calidad c WHERE c.producto.nombre LIKE %:nombreProducto%")
    List<Control_Calidad> findByProducto(@Param("nombreProducto") String nombreProducto);

    // Búsqueda por cliente (ajustado para una relación directa, si existe)
    @Query("SELECT c FROM Control_Calidad c JOIN c.cliente cl WHERE cl.nombre LIKE %:nombreCliente%")
    List<Control_Calidad> findByCliente(@Param("nombreCliente") String nombreCliente);

    // Búsqueda por fecha entre dos fechas (sin cambios)
    @Query("SELECT c FROM Control_Calidad c WHERE c.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Control_Calidad> findByFecha(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}