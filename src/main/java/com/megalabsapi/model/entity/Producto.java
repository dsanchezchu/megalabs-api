package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.megalabsapi.model.enums.StockStatus;
import java.util.List;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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