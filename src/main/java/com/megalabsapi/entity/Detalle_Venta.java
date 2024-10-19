package com.megalabsapi.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_Venta")
public class Detalle_Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Detalle_Venta")
    private Integer idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "ID_Venta", referencedColumnName = "ID_Venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Precio", nullable = false)
    private BigDecimal precio;
}