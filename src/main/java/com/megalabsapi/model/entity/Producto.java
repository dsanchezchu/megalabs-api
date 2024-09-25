package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.megalabsapi.model.enums.StockStatus;

@Data
@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @Column(name = "ID_Producto")
    private Integer idProducto;

    @Column(name = "Fecha_Venta")
    private java.sql.Date fechaVenta;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Stock", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus stock;
}