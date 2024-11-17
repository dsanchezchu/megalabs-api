package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Detalle_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DetalleVentaRepository extends JpaRepository<Detalle_Venta, Integer> {
    @Query("SELECT d.producto.idProducto, d.producto.nombre, d.producto.stock, SUM(d.cantidad) as totalVentas, AVG(d.precio) as precioPromedio " +
            "FROM Detalle_Venta d " +
            "GROUP BY d.producto.idProducto, d.producto.nombre, d.producto.stock")
    List<Object[]> obtenerReporteVentas();

}
