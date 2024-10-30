package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Detalle_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DetalleVentaRepository extends JpaRepository<Detalle_Venta, Integer> {
    @Query("SELECT d.producto.idProducto, SUM(d.cantidad) as totalVentas FROM Detalle_Venta d GROUP BY d.producto.idProducto")
    List<Object[]> obtenerReporteVentas();
}